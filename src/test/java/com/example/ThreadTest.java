package com.example;

import com.strat.springboot.Controller.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ThreadTest
 *
 * @author : Donald Cai
 * @date :   2017/5/7.
 */
public class ThreadTest {

    private static Object lock = new Object();

    private static Logger log = LoggerFactory.getLogger(ThreadTest.class);

    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();
        Thread thread1 = new Thread(runner1,"thread_test_1");
        Thread thread2 = new Thread(runner2,"thread_test_2");
        thread1.start();
        SleepUtils.second(1);
        thread2.start();
        while(true) {
            SleepUtils.second(1000);
        }
    }

    private static class Runner1 implements Runnable {

        @Override
        public void run() {
            log.info("runner1 start...");
            synchronized (lock) {
                log.info("runner1 into synchronized method...");
                while(flag) {
                    try {
                        log.info("runner1 start wait...");
                        lock.wait();
                        log.info("runner1 end wait...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("runner1 out synchronized method...");
            }
            int c = 3;
            while (c > 0) {
                SleepUtils.second(c--);
                log.info("c : " + c);
            }
            SleepUtils.second(2);
            log.info("runner1 end...");

        }
    }

    private static class Runner2 implements Runnable {

        @Override
        public void run() {
            log.info("runner2 start...");
            synchronized (lock) {
                log.info("runner2 into synchronized method...");
                int a = 3, b = 3;
                while (a > 0) {
                    SleepUtils.second(a--);
                    log.info("a : " + a);
                }
                log.info("runner2 notify...");
                lock.notifyAll();
                flag = false;
                while (b > 0) {
                    SleepUtils.second(b--);
                    log.info("b : " + b);
                }
                log.info("runner2 out synchronized method...");
            }
            synchronized (lock) {
                log.info("runner2 secondly into synchronized method...");
                SleepUtils.second(5);
                log.info("runner2 secondly out synchronized method...");
            }
            SleepUtils.second(2);
            log.info("runner2 end...");
        }
    }
}
