package com.ali.shali.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shali
 */
public class StringBigNum {
    StringBuilder sb;
    char[] nums, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int n, start, nineNums = 0;
    public  String printNumbers(int n) {
        this.n = n;
        start = n - 1;
        nums = new char[n];
        sb = new StringBuilder();
        dfs(0);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    void dfs(int index) {
        if (index == n) {
            //删除头部0
            String s = String.valueOf(nums).substring(start);
            if (!s.equals("0")) sb.append(s).append(",");
            //目前数位上全是9 该进位
            if (n - nineNums == start) --start;
            return;
        }
        for (char c : loop) {
            if (c == '9') ++nineNums;
            nums[index] = c;
            dfs(index + 1);
        }
        //回溯
        --nineNums;
    }

    public static void main(String[] args) {
        StringBigNum stringBigNum = new StringBigNum();
        String s = stringBigNum.printNumbers(8);
        System.out.println("s = " + s);
    }
}






