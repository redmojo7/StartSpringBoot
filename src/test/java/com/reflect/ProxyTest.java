package com.reflect;

import com.strat.springboot.Controller.domain.inter.IHello;
import com.strat.springboot.Controller.proxy.FacadeProxy;

/**
 * ProxyTest
 *
 * @author : Donald Cai
 * @date :   2017/5/14.
 */
public class ProxyTest {
    public static void main(String[] args) {
        IHello hello = FacadeProxy.newMapperProxy(IHello.class);
        System.out.println(hello.say("hello world"));
    }
}
