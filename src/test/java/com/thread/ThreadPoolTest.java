package com.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ThreadPoolTest
 *
 * @author : Donald Cai
 * @date :   2017/5/22.
 */
public class ThreadPoolTest {

    @Test
    public void main() {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(0,"aa");
        linkedList.add(1,"bb");
        linkedList.add(2,"cc");
        String first = linkedList.removeFirst();
        System.out.println("first : " + first);
    }
}
