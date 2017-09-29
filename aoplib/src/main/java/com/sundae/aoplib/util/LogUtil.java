package com.sundae.aoplib.util;

import android.util.Log;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

public class LogUtil {

    private static final boolean debug = true;
    private static final String TAG = "TAG";

    public static void e(String msg)
    {
        if(debug)
            Log.e(TAG , msg);
    }

    public static void i(String msg)
    {
        if(debug)
            Log.i(TAG , msg);
    }

    public static void w(String msg)
    {
        if(debug)
            Log.w(TAG , msg);
    }

}
