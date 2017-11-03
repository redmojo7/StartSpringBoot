package com.strat.springboot.controller.util;

import java.util.concurrent.TimeUnit;

/**
 * SleepUtils
 *
 * @author : Donald Cai
 * @date :   2017/5/7.
 */
public class  SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static final void milliSecond(long milliSecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
