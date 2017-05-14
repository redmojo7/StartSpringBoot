package com.reflect;

import com.strat.springboot.Controller.domain.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassTest
 *
 * @author : Donald Cai
 * @date :   2017/5/14.
 */
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        Class c1 = user.getClass();
        Class c2 = User.class;
        Class c3 = Class.forName("com.strat.springboot.Controller.domain.User");
        System.out.println(c1==c2);
        System.out.println(c2==c3);
        ///通过newInstance方法创建一个user实例对象，使用newInstance方法必须要有一个公共的构造方法
        User user2 = (User)c3.newInstance();
        System.out.println(user==user2);

        Annotation[] annotations = c1.getAnnotations();
        System.out.println("annotations : " + annotations);

        // Field
        user.setName("jeey");
        user.setAge(20);
        Field field = c1.getDeclaredField("name");

        //如下操作不能针对private属性，private只能通过get和set方法访问
        System.out.println("字段类型:"+field.getType().getName() );
        System.out.println("字段值："+field.get(user));
        field.set(user,"jock");
        System.out.println("修改后的字段属性值:"+user.getName());


        Method method = c1.getDeclaredMethod("getAge");
        System.out.println("获取age属性值："+ method.invoke(user));

        Method method1 = c1.getDeclaredMethod("setAge",int.class);
        method1.invoke(user,25);
        System.out.println("修改后的值："+user.getAge());

        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class);
        //通过构造方法创建实例
        User user3 = (User) constructor.newInstance("Donald",24);
        System.out.println("创建实例:name="+user3.getName()+",age="+user3.getAge());
    }
}
