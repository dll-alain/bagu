package com.ali.shali.juc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shali
 * @Date 2023/9/18 16:26
 * @PackageName:com.ali.shali.juc
 * @ClassName: BlockingQueue
 * @Description:
 * @Version 1.0
 */
public class BlockingQueue<T> {
    //1 任务队列
    private Deque<T> queue = new ArrayDeque<>();
    //2 锁
    private ReentrantLock lock = new ReentrantLock();

    //3 生成者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 阻塞添加
     * @param task
     */
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    System.out.println("wait task offered into queue...");
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("task offer to the queue.");
            queue.offer(task);
            emptyWaitSet.signal();

        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间的阻塞获取
     * @param task
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    System.out.println("wait task offered into queue...");
                    if (nanos <= 0) return false;
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("task offer to the queue.");
            queue.offer(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() == capacity) rejectPolicy.reject(this, task);
            else {
                //queue is not full
                System.out.println("任务加入任务队列");
                queue.offer(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取
     * @return 任务
     */
    public T get() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    System.out.println("task queue is empty...");
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T t = queue.poll();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间的阻塞获取
     * @param timeout
     * @param timeUnit
     * @return
     */
    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) return null;
                    System.out.println("task queue is empty...");
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T t = queue.poll();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }


    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
