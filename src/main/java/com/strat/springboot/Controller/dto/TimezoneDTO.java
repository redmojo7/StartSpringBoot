package com.strat.springboot.Controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Administrator on 2017/7/18.
 */
public class TimezoneDTO {
  @Min(1)
  @Max(100)
  private String id;
  
  @NotNull
  private String displayName;
  
  private String shortDisplayName;
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getDisplayName() {
    return displayName;
  }
  
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  
  
  public String getShortDisplayName() {
    return shortDisplayName;
  }
  
  public void setShortDisplayName(String shortDisplayName) {
    this.shortDisplayName = shortDisplayName;
  }
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
