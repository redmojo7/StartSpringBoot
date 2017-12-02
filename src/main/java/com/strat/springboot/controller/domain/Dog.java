package com.strat.springboot.controller.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Administrator on 2017/6/2.
 */
public class Dog {
  String color;

  public Dog() {
    color="yellow";
  }

  public Dog(String c) {
    color = c;
  }
  
  public String getColor() {
    return color;
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public String toString(){
    return color + " dog";
  }
  
  @Override
  public boolean equals(Object obj) {
    return ((Dog) obj).color == this.color;
  }
  
  @Override
  public int hashCode() {
    return color.length();
  }

  public void print() {
    System.out.printf("[Dog] : " + ToStringBuilder.reflectionToString(this));
  }
}
