package com.strat.springboot.Controller.enums;

/**
 * Created by Administrator on 2017/5/16.
 */
public enum SerialNumberEnum {
  SERIAL_NUMBER_BOL("BOL", "B"),
  SERIAL_NUMBER_FEEDBACK("Feedback", "R"),
  SERIAL_NUMBER_FLEET("Fleet", "C"),
  SERIAL_NUMBER_FLEETDRIVER("FleetDriver", "D"),
  SERIAL_NUMBER_IM("IM", "IM"),
  SERIAL_NUMBER_INVITECODE("InviteCode", "V"),
  SERIAL_NUMBER_INVOICE("Invoice", "N"),
  SERIAL_NUMBER_JOB("Job", "T"),
  SERIAL_NUMBER_OFFER("Offer", "F"),
  SERIAL_NUMBER_PAYOUT("Payout", "P"),
  SERIAL_NUMBER_SHIPMENT("Shipment", "S"),
  SERIAL_NUMBER_USER("User", "U");
  
  private String key;
  private String prefix;
  
  SerialNumberEnum(String redisKey, String prefix) {
    this.key = redisKey;
    this.prefix = prefix;
  }
  
  public String getKey() {
    return key;
  }
  
  public String getPrefix() {
    return prefix;
  }
  
}
