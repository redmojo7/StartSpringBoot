package com.strat.springboot.controller.enums;

/**
 * Created by Administrator on 2017/5/16.
 */
public enum SequenceSingleton {
  
  INSTANCE(1L);
  
  private long sequence;
  
  private SequenceSingleton(long seed) {
    this.sequence = seed;
  }
  
  public static SequenceSingleton getInstance() {
    return INSTANCE;
  }
  
  public synchronized long getNextNumber() {
    return sequence++;
  }
}
