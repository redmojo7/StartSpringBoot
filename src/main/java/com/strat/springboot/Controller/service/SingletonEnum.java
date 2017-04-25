package com.strat.springboot.Controller.service;

/**
 * SingletonEnum
 *
 * @author : Donald Cai
 * @date :   2017/4/22.
 */
public enum SingletonEnum {

    //枚举的过程是线程安全的，不存在同步的问题，这是实现singleton的最佳方法
    INSTANCE;

    public void fun1() {
        System.out.println("fun1...  " );
    }
}
