package com.example;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/22.
 */
public class ImmutableTest {
  
  @Test
  public void main() {
    ImmutableList<String> imList = ImmutableList.of("a", "b", "c");
    
    // imList.add("f");
    
    for (String s : imList) {
      System.out.println(s);
    }
    
    //常量Map
    ImmutableMap<String, String> imMap = new ImmutableMap.Builder<String, String>()
        .put("a", "1")
        .put("b", "2")
        .put("c", "3")
        .build();
    
    for (Entry<String, String> e : imMap.entrySet()) {
      System.out.println(e.getKey() + "=" + e.getValue());
    }
    
    //常量Set
    ImmutableSet<String> imSet = ImmutableSet.of("a", "b", "c");
    for (String s : imSet) {
      System.out.println(s);
    }
  }
  
}
