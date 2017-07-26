package com.atomic;

import com.strat.springboot.Controller.domain.User;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

/**
 * Created by Administrator on 2017/7/24.
 */
public class atomicTest {
 
    public static void main(String[] args) {
        float f1 = 1.11f;
        double d1 = 2.22d;
        
        int i1 = Float.floatToIntBits(f1);
        System.out.println("float to int bits  = " + i1);
        float f2 = Float.intBitsToFloat(i1);
        System.out.println("int bits to float  = " + f2);
        
        long l1 = Double.doubleToLongBits(d1);
        System.out.println("double to long bits  = " + l1);
        double d2 = Double.longBitsToDouble(l1);
        System.out.println("long bits to double = " + d2);
        
        
    }
    
    static int[] value = new int[] {1,2,3};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    
    @Test
    public void atomicArrayTest() {
        ai.getAndSet(0,3);
        System.out.println("ai = " + ai.get(0));
        System.out.println("ai = " + value[0]);
    }
    
    public static AtomicReference<User> atomicUserRef = new AtomicReference<>();
    
    @Test
    public void atomicReference() {
        
        User user = new User("lucy", 24);
        atomicUserRef.set(user);
        User userUpdate = new User("donald", 25);
        atomicUserRef.compareAndSet(user, userUpdate);
        System.out.println("user.name = " + atomicUserRef.get().getName());
        System.out.println("user.age = " + atomicUserRef.get().getAge());
    
        atomicUserRef.set(user);
        User userUpdate2 = new User("lily", 24);
        
        atomicUserRef.compareAndSet(userUpdate, userUpdate2);
        System.out.println("user.name = " + atomicUserRef.get().getName());
        System.out.println("user.age = " + atomicUserRef.get().getAge());
        
    }
    
    public static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
    
    @Test
    public void AtomicIntegerFieldUpdaterTest() {
        User conan = new User("conan", 10);
        System.out.println("user.age = " + a.getAndIncrement(conan));
        System.out.println("user.age = " + a.get(conan));
    }
}
