package com.strat.springboot.Controller.util;

import java.util.concurrent.TimeUnit;

/**
 * SleepUtils
 *
 * @author : Donald Cai
 * @date :   2017/5/7.
 */
public class  SleepUtils {

    public static void second(int timeValue) {
        try {
            TimeUnit.SECONDS.sleep(timeValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
