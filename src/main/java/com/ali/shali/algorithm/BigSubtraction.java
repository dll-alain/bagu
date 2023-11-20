package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/20 11:04
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: BigSubtraction
 * @Description: 大数减法
 * @Version 1.0
 */
public class BigSubtraction {

    public static void main(String[] args) {
        BigSubtraction bigSubtraction = new BigSubtraction();
        String subtraction = bigSubtraction.subtraction("1234", "234");
        System.out.println("subtraction = " + subtraction);
    }

    String subtraction(String num1, String num2) {
        char sign = '+';
        if (!compare(num1, num2)) {
            sign = '-';
            String temp = num1;
            num1 = num2;
            num2 = num1;
        }
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        char[] a1 = num1.toCharArray(), a2 = num2.toCharArray();
        StringBuilder bigNum = new StringBuilder();
        int borrow = 0;
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? a1[l1--] - '0' : 0;
            int y = l2 >= 0 ? a2[l2--] - '0' : 0;
            //if borrow a digit
            int temp = x - y - borrow;
            borrow = 0;
            if (temp < 0) {
                borrow = 1;
                temp += 10;
            }
            bigNum.append(temp);
        }
        bigNum.reverse();
        int index = 0;
        while (index < bigNum.length() && bigNum.charAt(index) == '0') index++;
        if (sign == '+') return bigNum.substring(index);
        else return sign + bigNum.substring(index);

    }


    boolean compare(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 < len2) return false;
        else if (len1 > len2) return true;
        else return num1.compareTo(num2) > 0;
    }
}
