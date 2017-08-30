package com.test;

import java.util.Arrays;
import java.util.List;

public class StringToListTest {
    public static void main(String[] args) {
        String abc = "aaa,bbb,ccc";
        String[] arr = abc.split(",");
        List<String> list = Arrays.asList(arr);
        for (String id:list
             ) {
            System.out.println("id===="+id);
        }

    }
}
