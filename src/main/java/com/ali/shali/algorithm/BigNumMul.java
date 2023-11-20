package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/20 11:51
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: BigNumMul
 * @Description: 大数乘法
 * @Version 1.0
 */
public class BigNumMul {

    String addStrings(String num1, String num2) {
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

    String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        String ans = "0";
        int l1 = num1.length(), l2 = num2.length();
        for (int i = l2 - 1; i >= 0; --i) {
            StringBuilder temp = new StringBuilder();
            int remainder = 0;
            //乘数高位补零
            for (int j = l2 - 1; j > i; j--) temp.append(0);
            int y = num2.charAt(i) - '0';
            for (int j = l1 - 1; j >= 0; --j) {
                int x = num1.charAt(j) - '0';
                int product = x * y + remainder;
                temp.append(product % 10);
                remainder = product / 10;
            }
            if (remainder != 0) temp.append(remainder % 10);
            ans = addStrings(ans, temp.reverse().toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        BigNumMul bigNumMul = new BigNumMul();
        String multiply = bigNumMul.multiply("33", "222");
        System.out.println("multiply = " + multiply);
    }
}
