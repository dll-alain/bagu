package com.ali.shali.algorithm;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author shali
 * @Date 2023/9/15 20:13
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: Main01
 * @Description: TODO
 * @Version 1.0
 */
public class Main01 {
    public  ListNode solve (ListNode[] a) {
        // write code here
        ListNode head = null;
        for (ListNode node : a) {
            head = merge(head, node);
        }
        return head;
    }

    ListNode merge(ListNode a, ListNode b) {
        if (a == null || b == null) return a != null ? a : b;
        ListNode head = new ListNode(0);
        ListNode ta = head, ap = a, bp = b;
        while (ap != null && bp != null) {
            if (ap.val < bp.val) {
                ta.next = ap;
                ap = ap.next;
            }
            else {
                ta.next = bp;
                bp = bp.next;
            }
            ta = ta.next;
        }
        ta.next = (ap != null ? ap : bp);
        return head.next;
    }

static class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

    public static void main(String[] args) {

    }

}
