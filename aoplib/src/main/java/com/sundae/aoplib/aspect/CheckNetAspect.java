package com.sundae.aoplib.aspect;

import android.content.Context;
import android.widget.Toast;

import com.sundae.aoplib.LibraryInfo;
import com.sundae.aoplib.annotation.CheckNet;
import com.sundae.aoplib.util.AppUtil;
import com.sundae.aoplib.util.LogUtil;
import com.sundae.aoplib.util.NetUtil;

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
public class CheckNetAspect {

    private static final String POINTCUT_METHOD = "execution(@" + LibraryInfo.PACKAGE_NAME + ".annotation.CheckNet * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodCheckNet() {}

    @Around("methodCheckNet()")
    public Object checkNet(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogUtil.e("checkNet");
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if(checkNet != null)
        {
//            LogUtil.e("checkNet1");
//            Class<?> cla = (Class<?>) proceedingJoinPoint.getThis();
//            String callbackMethodName = "";
//            if(callbackMethodName != null && callbackMethodName.length() > 0)
//            {
//                LogUtil.e("checkNet2");
//                Method callbackMethod = cla.getMethod(callbackMethodName , cla);
//                callbackMethod.setAccessible(true);
//                boolean intercept = (boolean) callbackMethod.invoke(cla);
//                LogUtil.e("intercept : " + intercept);
//            }
            Object obj = proceedingJoinPoint.getThis();     //获取这个方法所在的类
            Context context = AppUtil.getContext(obj);
            if(context != null)
            {
                if(!NetUtil.isNetworkAvailable(context))
                {
                    Toast.makeText(context,"请检查您的网络", Toast.LENGTH_LONG).show();
                    return null;  //返回null 拦截
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

}
