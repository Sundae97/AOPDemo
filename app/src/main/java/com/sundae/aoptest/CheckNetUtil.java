package com.sundae.aoptest;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by daijiyuan on 2017/9/22.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

@Aspect
public class CheckNetUtil {

    @Pointcut("execution(@com.sundae.aoptest.CheckNet * *(..))")
    public void CheckNetBehavior()
    {
    }

    @Around("CheckNetBehavior()")
    public Object checkNet(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.e("TAG" , "weaveJoinPoint  哇塞！！！");
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if(checkNet != null)
        {
            Log.e("TAG" , "checkNet != null");
            Object obj = proceedingJoinPoint.getThis();     //获取这个方法所在的类
            Context context = getContext(obj);
            if(context != null)
            {
                Log.e("TAG" , "context != null");
                if(!isNetworkAvailable(context))
                {
                    Toast.makeText(context,"请检查您的网络", Toast.LENGTH_LONG).show();
                    return null;  //返回null 拦截
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
