package com.sundae.aoplib.aspect;

import com.sundae.aoplib.LibraryInfo;
import com.sundae.aoplib.annotation.InterceptBefore;

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
public class InterceptBeforeBefore {

    private static final String POINTCUT_METHOD = "execution(@" + LibraryInfo.PACKAGE_NAME + ".annotation.InterceptBefore * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodCheckNet() {}

    @Around("methodCheckNet()")
    public Object interceptBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        InterceptBefore interceptBefore = methodSignature.getMethod().getAnnotation(InterceptBefore.class);
        if(interceptBefore != null)
        {
            String callbackMethodName = interceptBefore.beforeMethodName();
            if(callbackMethodName != null && callbackMethodName.length() > 0)
            {
                //TODO  获取方法  判断方法是否为空  调用方法  ， 尝试传递原调用方法参数
            }
        }
        return joinPoint.proceed();
    }
}
