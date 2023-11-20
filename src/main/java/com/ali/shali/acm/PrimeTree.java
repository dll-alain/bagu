package com.ali.shali.acm;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author shali
 * @Date 2023/8/18 17:47
 * @PackageName:com.ali.shali.acm
 * @ClassName: PrimeTree
 * @Description: TODO
 * @Version 1.0
 */
public class PrimeTree {
    static int[] w;
    static boolean[] prime;
    static List<List<Integer>> graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mx = 0;
        w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            mx = Math.max(mx, w[i]);
        }
        int size = 2 * mx + 1;
        prime = new boolean[size];
        getPrime(size);
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<Integer>());
        //n个点 n - 1 条边
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] ans = f(1, -1);
        System.out.println(Math.max(ans[0], ans[1]));

    }

    static int[] f(int v, int p) {
        int sum = 0;
        //res[0] 采用 res[1] 不采用
        int[] res = new int[2];
        List<int[]> temp = new ArrayList<>();
        for (int next : graph.get(v)) {
            if (next == p) continue;
            int[] sub = f(next, v);
            sum += Math.max(sub[0], sub[1]);
            res[0] += Math.max(sub[0] + (prime[w[v] + w[next]] ? 1 : 0), sub[1]);
            temp.add(new int[] {sub[0], sub[1], next});
        }
        for (int[] t : temp) {
            int mx = Math.max(t[0], t[1]);
            int other = sum - mx;
            if (!prime[w[v] + w[t[2]]]) continue;
            res[1] = Math.max(res[1], other + t[0] + 1);
        }
        return res;
    }

    static void getPrime(int n) {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                for (int j = i + i; j < n; j += i) {
                    //一个质数的倍数不为质数
                    prime[j] = false;
                }
            }
        }
    }
}
