package com.strat.springboot.Controller.mapStructs;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Administrator on 2017/6/8.
 */
public class Car {
  
  private String make;
  private int numberOfSeats;
  private CarType type;
  
  //constructor, getters, setters etc.
  public Car(String make, int numberOfSeats, CarType type) {
    this.make = make;
    this.numberOfSeats = numberOfSeats;
    this.type = type;
  }
  
  public String getMake() {
    return make;
  }
  
  public void setMake(String make) {
    this.make = make;
  }
  
  public int getNumberOfSeats() {
    return numberOfSeats;
  }
  
  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }
  
  public CarType getType() {
    return type;
  }
  
  public void setType(CarType type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}
