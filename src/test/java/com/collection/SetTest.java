package com.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public class SetTest {
  
  public static void add(List list, Object o) {
    list.add(o);
  }
  
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    add(list, 10);
    // String s = list.get(0);
    // System.out.println("s : " + s);
    
    //Illegal Code
    // Set<?> set = new HashSet<?>();
    
    ArrayList<String> al = new ArrayList<>();
    al.add("a");
    al.add("b");
    
    accept(al);
    
    ArrayList<Object> a2 = new ArrayList<>();
    a2.add("abc");
    test(a2);
  }
  
  public static void accept(ArrayList<?> al) {
    for (Object o : al) {
      System.out.println(o);
    }
  }
  
  public static void test(ArrayList<?> al) {
    for (Object e : al) {//no matter what type, it will be Object
      System.out.println(e);
// in this method, because we don't know what type ? is, we can not add anything to al.
    }
  }
  
}
