package com.strat.springboot.controller.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

/**
 * User
 *
 * @author : Donald Cai
 * @date :   2017/5/14.
 */
@Component("user")
public class User {

    private String name;
    //用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。volatile很容易被误用，用来进行原子性操作。
    public volatile int age;

    public User(){
    }
    public User(String name,int age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void print() {
        System.out.printf("[User] : " + ToStringBuilder.reflectionToString(this));
    }
}