package com.collection;

import com.strat.springboot.controller.domain.Dog;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Created by Administrator on 2017/6/2.
 */
public class MapTest {
  
    public static void main(String[] args) {
  
      System.out.println("HashMap test...");
      Map<Dog, Integer> map = new HashMap();    // is not synchronized.
      Dog d1 = new Dog("red");
      Dog d2 = new Dog("black");
      Dog d3 = new Dog("white");
      Dog d4 = new Dog("white");
      Dog d5 = new Dog("blue");
  
      map.put(d1, 10);
      map.put(d2, 15);
      map.put(d3, 5);
      map.put(d4, 20);
      // map.put(null, 21);
      // map.put(d5, null);
      
      
      //print size
      System.out.println(map.size());
      
      //loop HashMap
      for (Entry entry : map.entrySet()) {
        System.out.println(
            ( Objects.isNull(entry.getKey()) ? null : entry.getKey().toString() )
                + "\t\t-\t\t" + entry.getValue());
      }
  
      System.out.println("Hashtable test...");
      
      Map<Dog, Integer> hashtable = new Hashtable();  // is synchronized
      
      hashtable.put(d1, 12);
      // hashtable.put(null, 21);  // key :NullPointerException
      for (Entry entry : hashtable.entrySet()) {
        System.out.println(entry.getKey().toString() + "\t\t-\t\t" + entry.getValue());
      }
  
      System.out.println("linkedHashMap test...");
      
      LinkedHashMap<Dog, Integer> linkedHashMap = new LinkedHashMap();
      linkedHashMap.put(d1, 10);
      linkedHashMap.put(d2, 15);
      linkedHashMap.put(d3, 5);
      linkedHashMap.put(d4, 20);
  
      for (Entry entry : linkedHashMap.entrySet()) {
        System.out.println(entry.getKey() + " - " + entry.getValue());
      }
  
      System.out.println("HashMap test...");
    }
}
