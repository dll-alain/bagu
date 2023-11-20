package com.ali.shali.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author shali
 * @Date 2023/8/25 16:25
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: Demo1
 * @Description: TODO
 * @Version 1.0
 */
public class Demo1 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        String s = demo1.minNumber(new int[]{11, 111, 232, 342, 45623});
        System.out.println("s = " + s);
    }

    public String minNumber(int[] nums) {
        String[] strs = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        while (i < j) {
            //alawys r to l
            while (i < j && (strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0) j--;
            while (i < j && (strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0) i++;
            swap(strs, i, j);
        }
        swap(strs, l, i);
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }

    void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }
}
