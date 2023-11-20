package com.ali.shali.algorithm;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shali
 * @Date 2023/8/22 09:52
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: AlternatePrintOneHundred
 * @Description: 交替打出1 - 100
 * @Version 1.0
 */
public class AlternatePrintOneHundred {

    static class Task implements Runnable {

        public Task(int threadNo) {
            this.threadNo = threadNo;
        }

        public static final int NUM_OF_THREAD = 10;

        public static final int MAX_NUM = 100;

        public static AtomicInteger counter = new AtomicInteger(1);

        //only this one belong to instance
        int threadNo;

        public static final ReentrantLock lock = new ReentrantLock();

        public static final Condition condition = lock.newCondition();

        boolean isValid(int counter) {
            return (counter == threadNo)
                    || (counter % NUM_OF_THREAD == threadNo)
                    || ((counter % NUM_OF_THREAD == 0) && (threadNo == NUM_OF_THREAD));
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (counter.intValue() <= MAX_NUM) {
                    //1 counter == threadNo / 1-9
                    //2 1 % 10 = 1 11-99 exclude /10,20 ...
                    //3 10 % 10 = 0 && 10 % 100 = 0 /10 20 -100
                    if (isValid(counter.intValue())) {
                        System.out.println("Thread_" + threadNo + " printing " + counter.getAndAdd(1));
                        condition.signalAll();
                    }
                    else {
                        try {
                            while (!isValid(counter.intValue())) {
                                condition.await();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                Task.NUM_OF_THREAD,
                Task.NUM_OF_THREAD,
                1L,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 1; i <= Task.NUM_OF_THREAD; i++) {
            threadPoolExecutor.execute(new Task(i));
        }
    }
}
