package com.ali.shali.acm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author shali
 * @Date 2023/8/18 10:19
 * @PackageName:com.ali.shali.acm
 * @ClassName: TSRING
 * @Description: TODO
 * @Version 1.0
 */
public class TSRING {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] s = scanner.nextLine().toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : s) set.add(c);
        char[] t = new char[s.length];
        int pos = 0;
        Set<Character> usd = new HashSet<>();
        for (int j = 0; j < s.length; j++) {
            //优先使用未使用的C
            if (usd.size() != set.size()) {
                for (char c : set) {
                    if (!usd.contains(c) && c != s[j]) {
                        t[j] = c;
                        usd.add(c);
                        break;
                    }
                }
            }
            else {
                //使用完后，使用任意不同的
                for (char c : set) {
                    if (c != s[j]) {
                        t[j] = c;
                        break;
                    }
                }
            }
        }
        System.out.println(String.valueOf(t));
    }
}
