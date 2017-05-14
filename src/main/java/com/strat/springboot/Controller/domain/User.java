package com.strat.springboot.Controller.domain;

import org.springframework.stereotype.Component;

/**
 * User
 *
 * @author : Donald Cai
 * @date :   2017/5/14.
 */
@Component("userDomain")
public class User {

    public String name;
    private int age;

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
}