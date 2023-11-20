package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/22 16:44
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: QuickMP
 * @Description: TODO
 * @Version 1.0
 */
public class QuickMP {

    public static void main(String[] args) {
        int i = QuickMP.fastMul(-3, 4);
        System.out.println("i = " + i);
    }

    public static int fastMul(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) res += a;
            a <<= 1;
            b >>= 1;
        }
        return res;
    }

    public static double quickPow(double x, int n) {
        if (x == 0 || x == 1) return x;
        return  n > 0 ? fastPow(x, n) : 1 / fastPow(x, -n);
    }

    static double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double y = fastPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    static double myPow(double x, int n) {
        long y = (n < 0) ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) res *= x;
            x *= x;
            y >>= 1;
        }
        return (n < 0) ? (1 / res) : res;
    }
}
