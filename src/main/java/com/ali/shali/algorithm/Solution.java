package com.ali.shali.algorithm;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        getLeastNumbers(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 4);
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        quickSort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }


    static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        while (i < j) {
            //alawys right first
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, l, i);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}