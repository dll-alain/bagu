package com.ali.shali.algorithm;

import java.util.Arrays;

/**
 * @Author shali
 * @Date 2023/9/18 11:35
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: HeapSort
 * @Description: 堆排序
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr =  new int[] {1, 3, 2, 111, 23, 444, 77, 5};
        heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    void heapSort(int[] arr) {
        int len = arr.length;
        buildHeap(arr, len);
        for (int i = len - 1; i > 0; --i) {
            swap(arr, i, 0);
            adjustHeap(arr, 0, i);
        }
    }


    /**
     * 建堆 冲下向上，从最后一个有孩子节点的父节点开始
     * i = (len - 1 - 1) / 2
     * 时间复杂度 O(n)
     * @param arr 排序数组
     * @param len 数组长度
     */
    void buildHeap(int[] arr, int len) {
        for (int i = (len - 1 - 1) / 2; i >= 0; --i) adjustHeap(arr, i, len);
    }


    /**
     * @param arr 排序数组
     * @param i 父节点
     * @param len 数组长度 堆排序数组长度是变化的
     */
    void adjustHeap(int[] arr, int i, int len) {
        int largest = i, lSon = i * 2 + 1, rSon = i * 2 + 2;
        if (lSon < len && arr[lSon] > arr[largest]) largest = lSon;
        if (rSon < len && arr[rSon] > arr[largest]) largest = rSon;
        if (largest != i) {
            swap(arr, largest, i);
            adjustHeap(arr, largest, len);
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
