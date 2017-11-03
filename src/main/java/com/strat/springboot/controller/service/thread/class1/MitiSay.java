package com.strat.springboot.controller.service.thread.class1;

/**
 * Created by Administrator on 2017/5/4.
 */
class MitiSay extends Thread {
  
  public MitiSay(String threadName) {
    super(threadName);
  }
  
  public void run() {
    System.out.println(getName() + " 线程运行开始!");
    for (int i = 0; i < 100; i++) {
      System.out.println(i + " " + getName());
      try {
        sleep((int) Math.random() * 10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(getName() + " 线程运行结束!");
  }
}
