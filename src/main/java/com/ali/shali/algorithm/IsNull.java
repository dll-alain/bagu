package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/11 17:08
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: IsNull
 * @Description: TODO
 * @Version 1.0
 */
public class IsNull {

    public static void main(String[] args) {
        Integer[] arr = new Integer[3];
        arr[0] = null;
        arr[1] = 1;
        arr[2] = 2;
        int length = arr.length;
        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
