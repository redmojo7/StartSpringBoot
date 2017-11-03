package com.strat.springboot.controller.service;

import java.util.Date;
import java.util.Objects;

/**
 * SingletonDemo
 *
 * @author : Donald Cai
 * @date :   2017/4/22.
 */
public class SingletonDemo {

    private static volatile SingletonDemo defaultInstance;

    //饿汉模式
//    private static final SingletonDemo defaultInstance = new SingletonDemo();

    private SingletonDemo(){
        System.out.println(new Date().toString() +" : SingletonDemo default construction method is invoked.");
    }

    //懒汉模式，按需加载
    public static SingletonDemo getDefault() {
        if (Objects.isNull(defaultInstance)) {
            synchronized (SingletonDemo.class) {
                if (Objects.isNull(defaultInstance)) {
                    defaultInstance = new SingletonDemo();
                }
            }
        }
        return defaultInstance;
    }

    //饿汉模式
//    public static SingletonDemo getDefaultInstance(){
//        return defaultInstance;
//    }


    public void printText(String text) {
        System.out.println("text : " + text);
    }
}
