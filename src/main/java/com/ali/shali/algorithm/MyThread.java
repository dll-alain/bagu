package com.ali.shali.algorithm;



import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shali
 * @Date 2023/8/21 12:00
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: MyRunnable
 * @Description: TODO
 * @Version 1.0
 */
public class MyThread extends Thread{

    /**
     * define the Total No.Of Threads needed
     */
    public static final int TOTAL_THREAD = 10;

    public final static Object obj = new Object();

    int threadNo;


    /**
     * importance outer nums
     */
    static volatile int counter = 1;

    public MyThread(int threadNo) {
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        //in a synchronized block to acquire lock
        /*
         * counter==threadNo => To print the initial numbers till TOTAL_THREADS
         * counter%TOTAL_THREADS == threadNo => e.g. 11%10 = 1 -> 1 will print this, 12%10 = 2 ...
         * (counter%TOTAL_THREADS == 0) && (TOTAL_THREADS == threadNo) => 10%10 will be 0,
         *              and this must be printed by 10 th thread only, ie the highest thread.
         */
        synchronized (obj) {
            while (counter <= 100) {
                if (counter == threadNo
                        || (counter % TOTAL_THREAD == threadNo)
                        || ((counter % TOTAL_THREAD == 0) && (TOTAL_THREAD == threadNo))
                ) {
                    //Display the output as desired
                    System.out.println(this.threadNo + " printing " + counter++);
                    //notify
                    obj.notifyAll();
                }
                else {
                    //current thread not eligible for printing the current counter value, so wait till its notified
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= TOTAL_THREAD; i++) {
            MyThread thread = new MyThread(i);
            thread.start();
        }
    }
}
