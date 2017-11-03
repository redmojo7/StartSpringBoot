package com.strat.springboot.controller.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/6/9.
 */
@Entity
@Transactional
public class Users {
  
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private int id;
  private String uname;
  private String pass;
  private String name;
  private byte year;
  public String getName() {
    return name;
  }
  
  
  public void setName(String name) {
    this.name = name;
  }
  
  
  public byte getYear() {
    return year;
  }
  
  
  public void setYear(byte year) {
    this.year = year;
  }
  
  
  public Users(String uname, String pass, String name, byte year) {
    super();
    
    this.uname = uname;
    this.pass = pass;
    this.name = name;
    this.year = year;
  }
  
  
  public Users() {
    super();
  }
  
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
  
  public String getUname() {
    return uname;
  }
  public void setUname(String uname) {
    this.uname = uname;
  }
  public String getPass() {
    return pass;
  }
  public void setPass(String pass) {
    this.pass = pass;
  }
  
  
  @Override
  public String toString() {
    return "users [id=" + id + ", uname=" + uname + ", pass=" + pass +
        ", name=" + name + ", year=" + year + "]";
  }
  
  
  
  
  
}
