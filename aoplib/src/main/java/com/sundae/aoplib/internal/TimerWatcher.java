package com.sundae.aoplib.internal;

import java.util.concurrent.TimeUnit;

/**
 * Created by daijiyuan on 2017/9/29.
 * 邮箱 948820549@qq.com
 *
 * @Sundae
 */

public class TimerWatcher {

    private long startTime;
    private long endTime;
    private long runningTime;

    public TimerWatcher() {
//        startTime = 0;
//        endTime = 0;
//        runningTime = 0;
    }

    private void reset() {
        startTime = 0;
        endTime = 0;
        runningTime = 0;
    }

    public long start()
    {
        startTime = System.nanoTime();
        return startTime;
    }

    public long stop()
    {
        if(startTime == 0) return 0;
        endTime = System.nanoTime();
        return endTime;
    }

    public long getRunningTime()
    {
        return (startTime == 0 || endTime == 0) ? 0 : TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
    }

}
