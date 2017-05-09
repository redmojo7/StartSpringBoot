package com.strat.springboot.Controller.util;

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
}
