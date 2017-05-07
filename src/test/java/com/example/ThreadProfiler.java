package com.example;

import com.strat.springboot.Controller.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadProfiler
 *
 * @author : Donald Cai
 * @date :   2017/5/7.
 */
public class ThreadProfiler {

    private static Logger log = LoggerFactory.getLogger(ThreadTest.class);

    private static final ThreadLocal<Long> timer = new ThreadLocal<Long>() {
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        timer.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - timer.get();
    }

    public static void main (String[] args) {
        ThreadProfiler.begin();
        SleepUtils.second(1);
        log.info("Cost : {} mills.", ThreadProfiler.end());
    }

}
