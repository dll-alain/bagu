package com.ali.shali.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shali
 * @Date 2023/8/10 10:20
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: StringToIP
 * @Description: 字符串转IP
 * @Version 1.0
 */
public class StringToIP {

    List<String> res = new ArrayList<>();

    List<String>  stringToIP(String s) {
        if (s.length() > 12) return res;
        dfs(s, 0, 0);
        return  res;
    }

    /**
     * 思路 攒满三个点 四段三点
     * @param s
     * @param index
     * @param pointNums
     */
    void dfs(String s, int index, int pointNums) {
        if (index > s.length() - 1) return;
        if (pointNums == 3) {
            if (isValid(s, index, s.length() - 1)) res.add(s);
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i))
            {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNums++;
                dfs(s, index + 2, pointNums);
                pointNums--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }
            else break;

        }
    }

    boolean isValid(String s, int start, int end) {
        if (start > end) return false;
        //不能以0开头,除了0
        if (s.charAt(start) == '0' && start != end) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;
            num = 10 * num + (s.charAt(i) - '0');
            if (num > 255) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        StringToIP stringToIP = new StringToIP();
        List<String> strings = stringToIP.stringToIP("25525511135");
        strings.forEach(System.out::println);
    }
}

