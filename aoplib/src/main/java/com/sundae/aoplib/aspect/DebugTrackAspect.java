package com.sundae.aoplib.aspect;

import com.sundae.aoplib.LibraryInfo;
import com.sundae.aoplib.internal.TimerWatcher;
import com.sundae.aoplib.util.LogUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

@Aspect
public class DebugTrackAspect {

    private static final String POINTCUT_METHOD = "execution(@"+ LibraryInfo.PACKAGE_NAME +".annotation.DebugTrack * *(..))";
    private static final String POINTCUT_CONSTRUCTOR = "execution(@"+ LibraryInfo.PACKAGE_NAME +".annotation.DebugTrack *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodDebugTrack() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorDebugTrack() {}

    @Around("methodDebugTrack() || constructorDebugTrack()")
    public Object debugTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getName();

        TimerWatcher timerWatcher = new TimerWatcher();
        timerWatcher.start();
        Object result = joinPoint.proceed();
        timerWatcher.stop();

        outputLogText(className , methodName , timerWatcher.getRunningTime());
        return result;
    }

    public void outputLogText(String className , String methodName , long time)
    {
        LogUtil.e("------------------------------------------------");
        LogUtil.e("  ClassName   : " + className                    );
        LogUtil.e("  MethodName  : " + methodName                   );
        LogUtil.e("  RunningTime : " + time                         );
        LogUtil.e("------------------------------------------------");
    }

}
