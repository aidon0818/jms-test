package com.test;

import java.util.HashMap;
import java.util.Map;

/**
 * forEach 使用方式
 * */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> items = new HashMap<String, Integer>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k,v)->System.out.println("key : " + k + "; value : " + v));

    }
}
