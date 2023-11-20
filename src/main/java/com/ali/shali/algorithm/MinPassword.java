package com.ali.shali.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shali
 * @Date 2023/9/22 11:07
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: MinPassword
 * @Description: TODO
 * @Version 1.0
 */
public class MinPassword {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
    }

    public String crackPassword(int[] password) {
        String[] arr = (String[]) Arrays.stream(password).mapToObj(String::valueOf).toArray(String[]::new);
        stringQuickSort(arr, 0, password.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }


    void stringQuickSort(String[] arr, int l, int r) {
        if (l >= r) return;
        int i = l ,j = r;
        while (i < j) {
            //right first
            while (i < j && (arr[l] + arr[j]).compareTo(arr[j] + arr[l]) <= 0) j--;
            while (i < j && (arr[i] + arr[l]).compareTo(arr[l] + arr[i]) >= 0) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        stringQuickSort(arr, l, i - 1);
        stringQuickSort(arr, i + 1, r);
    }

    void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
