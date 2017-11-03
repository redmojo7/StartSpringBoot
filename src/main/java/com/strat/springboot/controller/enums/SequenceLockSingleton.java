package com.strat.springboot.controller.enums;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/16.
 */
public enum SequenceLockSingleton {
  
  INSTANCE(1L);
  
  private long sequence;
  
  private Lock lock = new ReentrantLock();
  
  private SequenceLockSingleton(long seed) {
    this.sequence = seed;
  }
  
  public static SequenceLockSingleton getInstance() {
    return INSTANCE;
  }
  
  public long getNextNumber() {
    lock.lock();
    try {
      return sequence++;
    } finally {
      lock.unlock();
    }
  }
}
