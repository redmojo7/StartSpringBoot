package com.format;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @Author : Donald
 * @Description :
 * @Date : 2017/9/27 16:34.
 */
public class currentFormat {
    
    public static void main(String[] args) {
        NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("US 1000000 = " + GBP.format(1000000) );
    }
}
