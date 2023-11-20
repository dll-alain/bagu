package com.ali.shali.juc;

import com.ali.shali.algorithm.MyThread;

import java.util.concurrent.TimeUnit;

/**
 * @Author shali
 * @Date 2023/9/13 15:32
 * @PackageName:com.ali.shali.juc
 * @ClassName: Demo01
 * @Description: 死锁
 * @Version 1.0
 */
public class Demo01 {
    public static void main(String[] args) {
        Object La = new Object();
        Object Lb = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (La) {
                System.out.println("线程A获取锁A。");
                try {
                    /**
                     * TimeUnit是一个时间工具类，更好的帮助我们定义休眠时间
                     *TimeUnit.DAYS //天
                     * TimeUnit.HOURS //小时
                     * TimeUnit.MINUTES //分钟
                     * TimeUnit.SECONDS //秒
                     * TimeUnit.MILLISECONDS //毫秒
                     * TimeUnit.NANOSECONDS //毫微秒
                     * TimeUnit.MICROSECONDS //微秒
                     */
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //对象A苏醒之后再去尝试获取锁B
                synchronized (Lb) {
                    System.out.println("线程A尝试获取锁B。");
                }
            }
        });
        threadA.start();
        Thread threadB = new Thread(() -> {
           synchronized (Lb) {
               System.out.println("线程B获取锁B。");
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               //对象B苏醒之后再去尝试获取锁A
               synchronized (La) {
                   System.out.println("线程B尝试获取锁A。");
               }
           }
        });
        threadB.start();
    }

}
