package com.example;

import com.strat.springboot.controller.enums.SerialNumberEnum;
import com.strat.springboot.controller.util.SleepUtils;

/**
 * Created by Administrator on 2017/5/16.
 */
public class EnumSyncTest {
  
  // private static final int AMOUNT_OF_NUMBERS = 100000;
  private static final int AMOUNT_OF_NUMBERS = 1;
  private static final int NUMBER_OF_THREADS = 50;
  
  private static long num = 0;
  
  public static void main(String... args) {
    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      Thread numberThread = new Thread(new GetNumbersForAWhile());
      numberThread.start();
    }
    SleepUtils.second(6);
    System.out.println(" ..........................num : " + num);
    
  }
  
  private static class GetNumbersForAWhile implements Runnable {
    
    @Override
    public void run() {
      long startTime = System.currentTimeMillis();
      synchronized (SerialNumberEnum.SERIAL_NUMBER_USER) {
        System.out.print(Thread.currentThread().getName() + " start ");
        SleepUtils.milliSecond(100);
        num++;
        System.out.println(Thread.currentThread().getName() + " end ");
        
        
      }
      long endTime = System.currentTimeMillis();
      System.out.println("Took: " + (endTime - startTime) + "ms.");
    }
  }
}
