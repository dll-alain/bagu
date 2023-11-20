package com.ali.shali.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shali
 * @Date 2023/9/14 16:29
 * @PackageName:com.ali.shali.algorithm
 * @ClassName: Demo02
 * @Description: ReentrantLock解决死锁
 * @Version 1.0
 */
public class Demo02 {

    public static void main(String[] args) {
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();

        Thread threadA = new Thread(() -> {
            while (true) {
                if (lockA.tryLock()) {
                    System.out.println("线程A获取锁A");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        if (lockB.tryLock()) {
                            try {
                                System.out.println("线程A获得锁B");
                            } finally {
                                lockB.unlock();
                                System.out.println("线程A释放锁B");
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lockA.unlock();
                        System.out.println("线程A释放锁A");
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
           while (true) {
               if (lockB.tryLock()) {
                   System.out.println("线程B获取锁B。");
                   try {
                       TimeUnit.SECONDS.sleep(1);
                       if (lockA.tryLock()) {
                           try {
                               System.out.println("线程B获取锁A");
                           } finally {
                               lockA.unlock();
                               System.out.println("线程B释放锁A");
                               break;
                           }
                       }
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   } finally {
                       lockB.unlock();
                       System.out.println("线程B释放锁B");
                   }
               }
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });
        threadB.start();
    }
}
