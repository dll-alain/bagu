package com.ali.shali.algorithm;

import java.util.Arrays;

/**
 * @Author shali
 * @Date 2023/9/18 14:19
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: QuickSort
 * @Description: 快排
 * @Version 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] ints = {3, 2, 11, 44, 55, 22, 1, 5, 3, 7};
        quickSort.quickSort(ints, 0, ints.length - 1);
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
    }

    void quickSort(int[] arr, int l, int r) {
        if (l > r) return;
        int i = l, j = r;
        while (i < j) {
            //right first
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, l, i);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
