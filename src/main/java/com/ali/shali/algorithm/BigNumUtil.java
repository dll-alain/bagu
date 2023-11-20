package com.ali.shali.algorithm;

import java.io.OutputStream;

/**
 * @Author shali
 * @Date 2023/9/20 15:27
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: BigNumUtil
 * @Description: 大数四则运算
 * @Version 1.0
 */
public class BigNumUtil {

    private static boolean compare(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 < len2) return false;
        else if (len1 > len2) return true;
        else return num1.compareTo(num2) > 0;
    }

    public static String bigAdd(String num1, String num2) {
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

    public static String bigSubtraction(String num1, String num2) {
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

    public static String bigMultiply(String num1, String num2) {
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
            ans = bigAdd(ans, temp.reverse().toString());
        }
        return ans;
    }

    public static String bigDivide(String num1, String num2) {
        String ans = "0";
        while (compare(num1, num2)) {
            //用这个往后面不断加0，和num做减法
            StringBuilder item = new StringBuilder(num2);
            //一次减次数 maybe很大
            StringBuilder count = new StringBuilder("1");
            //统计大概要加几个零
            int subLen = num1.length() - num2.length();
            for (int i = 0; i < subLen; ++i) {
                item.append('0');
                count.append('0');
            }
            //如果0 加多了 那么要删一个 类似"12300" / "23" "12300"比"23000"小
            //所以要 "2300" 对应比"23"扩大 "100"倍数，每减一次"2300" 则结果加"100"
            if (!compare(num1, item.toString())) {
                item.deleteCharAt(item.length() - 1);
                count.deleteCharAt(count.length() - 1);
            }
            while (compare(num1, item.toString())) {
                num1 = bigSubtraction(num1, item.toString());
                ans = bigAdd(ans, count.toString());
            }
        }
        return ans;
    }
}
