package com.ali.shali.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author shali
 * @Date 2023/9/11 15:14
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: StreamVsFor
 * @Description: TODO
 * @Version 1.0
 */
public class StreamVsFor {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 101; i++) list.add(i);
        List<Integer> collect = list.stream().map(integer -> integer * 2).peek(System.out::println).collect(Collectors.toList());
        System.out.println("--------------------");
        collect.forEach(System.out::println);
        System.out.println("--------------------");
        for (Integer integer : collect) {
            System.out.println(integer);
        }
        System.out.println("--------------------");
        for (int i = 0; i < collect.size(); i++) {
            System.out.println(collect.get(i));
        }


    }
}
