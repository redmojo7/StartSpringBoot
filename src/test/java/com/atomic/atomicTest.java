package com.atomic;

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
}
