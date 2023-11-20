package com.ali.shali.algorithm;

/**
 * @Author shali
 * @Date 2023/9/22 16:25
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: IntDivide
 * @Description: TODO
 * @Version 1.0
 */
public class IntDivide {

    boolean quickMul(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int res = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                if (res < x - add) return false;
                res += add;
            }
        }

        return false;
    }


}
