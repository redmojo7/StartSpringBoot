package com.strat.springboot.Controller.enums;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/5/16.
 */
public enum SequenceAtomicSingleton {
  
  INSTANCE(1L);
  
  private AtomicLong sequence;
  
  private SequenceAtomicSingleton(long seed) {
    this.sequence = new AtomicLong(seed);
  }
  
  public static SequenceAtomicSingleton getInstance() {
    return INSTANCE;
  }
  
  public long getNextNumber() {
    return sequence.getAndAdd(1L);
  }
  
}
