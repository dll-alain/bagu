package com.ali.shali.temp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @Author shali
 * @Date 2023/10/19 17:32
 * @PackageName:com.ali.shali.temp
 * @ClassName: Demo1
 * @Description: TODO
 * @Version 1.0
 */
public class Demo1 {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        List<Integer> collect = Arrays.stream(arr).filter(i -> i != 3).collect(Collectors.toList());
        collect.forEach(System.out::println);
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Arrays.stream(arr1).filter(i -> i != 3).boxed().collect(Collectors.toList());
    }
}
