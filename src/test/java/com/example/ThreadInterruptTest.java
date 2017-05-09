package com.example;

import com.strat.springboot.Controller.util.SleepUtils;

/**
 * ThreadInterruptTest
 *
 * @author : Donald Cai
 * @date :   2017/5/10.
 */
public class ThreadInterruptTest {
    public static void main(String[] args) {
        Thread sleetThread = new Thread(new SleepRunner(),"sleepThread");
        sleetThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(),"busyThread");
        busyThread.setDaemon(true);
        sleetThread.start();
        busyThread.start();
        SleepUtils.second(5);
        sleetThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupt is " + sleetThread.isInterrupted());
        System.out.println("busyThread interrupt is " + busyThread.isInterrupted());
        SleepUtils.second(10);
    }
    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while(true){

            }
        }
    }
    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while(true){
                SleepUtils.second(2);
            }
        }
    }
}
