package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/20 14:27
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: BigAdd
 * @Description: 大数加法
 * @Version 1.0
 */
public class BigAdd {
    public String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1, l2 = num2.length() - 1, remainder = 0;
        char[] arr1 = num1.toCharArray(), arr2 = num2.toCharArray();
        StringBuilder bigNum = new StringBuilder();
        while(l1 >= 0 || l2 >= 0 || remainder != 0) {
            int x = l1 >= 0 ? arr1[l1--] - '0' : 0;
            int y = l2 >= 0 ? arr2[l2--] - '0' : 0;
            int temp = x + y + remainder;
            bigNum.append(temp % 10);
            remainder = temp / 10;
        }
        return bigNum.reverse().toString();
    }
}
