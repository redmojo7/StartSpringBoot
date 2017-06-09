package com.strat.springboot.Controller.mapStructs;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Administrator on 2017/6/8.
 */
public class CarDto {
  private String make;
  private int seatCount;
  private String type;
  
  //constructor, getters, setters etc.
  public CarDto(String make, int seatCount, String type) {
    this.make = make;
    this.seatCount = seatCount;
    this.type = type;
  }
  
  public CarDto() {
  
  }
  
  public String getMake() {
    return make;
  }
  
  public void setMake(String make) {
    this.make = make;
  }
  
  public int getSeatCount() {
    return seatCount;
  }
  
  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
