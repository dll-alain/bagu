package com.ali.shali.algorithm;

import java.math.BigDecimal;

/**
 * @Author shali
 * @Date 2023/9/20 10:11
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: BigDivide
 * @Description: 大数除法
 * @Version 1.0
 */
public class BigDivide {

    BigSubtraction bigSubtraction = new BigSubtraction();
    BigAdd bigAdd = new BigAdd();

    boolean compare(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 < len2) return false;
        else if (len1 > len2) return true;
        else return num1.compareTo(num2) > 0;
    }

    String divideString(String num1, String num2) {
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
                num1 = bigSubtraction.subtraction(num1, item.toString());
                ans = bigAdd.addStrings(ans, count.toString());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BigDivide bigDivide = new BigDivide();
        String s = bigDivide.divideString("12345678", "3");
        System.out.println("s = " + s);
    }

}
