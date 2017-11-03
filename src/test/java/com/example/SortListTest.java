package com.example;

import com.google.common.collect.Maps;
import com.strat.springboot.controller.domain.User;
import com.strat.springboot.controller.util.SortList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/5/22.
 */
public class SortListTest {
  
  @Test
  public void main() {
    List<User> users = new ArrayList<>();
    users.add(new User("aa",19));
    users.add(new User("bb",23));
    users.add(new User("nk",20));
    SortList<User> sortList = new SortList<User>();
    sortList.sort(users, "getAge", "desc");
    System.out.println("getAge : " + StringUtils.collectionToDelimitedString(users,","));
    sortList.sort(users, "getName", "desc");
    System.out.println("getName : " + StringUtils.collectionToDelimitedString(users,","));
  
    Map.Entry<Long, String> map = Maps.immutableEntry(200L, "OK!");
    System.out.println("map : " + map );
  
  }
  
}
