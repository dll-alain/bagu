package com.ali.shali.lc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test01 {

    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.stream().mapToInt().toArray()
    }

}

class CQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public CQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        dump();
        return outStack.pop();
    }

    void dump() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
