package com.ali.shali.leetcode;

/**
 * @Author shali
 * @Date 2023/11/13 11:45
 * @Project:shali
 * @Description:
 * @Version 1.0
 */
public class D1113 {
    int[] arr;
    int totalSum;

    public D1113(int[] nums) {
        arr  = nums;
        for (int num : nums) totalSum += num;
    }

    public void update(int index, int val) {
        if (index < 0 || index >= arr.length) return;
        totalSum += (val - arr[index]);
        arr[index] = val;
    }

    public int sumRange(int left, int right) {
        int ans = totalSum;
        while (left >= 0 || right < arr.length) {
            if (--left >= 0) ans -= arr[left];
            if (++right < arr.length) ans -= arr[right];
        }
        return ans;
    }

    public static void main(String[] args) {
        D1113 d1113 = new D1113(new int[]{9, -8});
        d1113.update(0, 3);
        d1113.sumRange(1, 1);
    }
}
