package com.sundae.aoplib.aspect;

import com.sundae.aoplib.LibraryInfo;
import com.sundae.aoplib.util.LogUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */
@Aspect
public class InterceptBeforeBefore {

    private static final String POINTCUT_METHOD = "@annotation(" + LibraryInfo.PACKAGE_NAME + ".annotation.InterceptBefore(callbackMethodName))";

    @Around(value = POINTCUT_METHOD , argNames = "callbackMethodName")
    public Object interceptBefore(ProceedingJoinPoint joinPoint , String callbackMethodName) throws Throwable {
        LogUtil.e("interceptBefore + "+ callbackMethodName);
        if(callbackMethodName != null&& callbackMethodName.length() > 0)
        {
            Object obj = joinPoint.getThis();
            Class<?> cla = obj.getClass();
            try{
                Method callbackMethod = cla.getDeclaredMethod(callbackMethodName);
                if(callbackMethod != null)
                {
                    callbackMethod.setAccessible(true);
                    boolean result = (boolean) callbackMethod.invoke(obj);
                    if(!result)
                        return null;
                }
            }catch (Exception e)
            {
                LogUtil.e("interceptBefore e : " + e.toString());
                return joinPoint.proceed();
            }
        }
        return joinPoint.proceed();
    }

}
