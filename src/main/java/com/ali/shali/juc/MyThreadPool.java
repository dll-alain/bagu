package com.ali.shali.juc;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @Author shali
 * @Date 2023/9/15 17:54
 * @PackageName:com.ali.shali.juc
 * @ClassName: MyThreadPool
 * @Description: 手写线程池
 *
 * @methods:
 * execute()
 * 当任务数没有超过核心数的时候就直接交给worker执行(创建线程worker)，反之则加入任务队列暂存起来。不过要注意我们的workers是需要是线程安全的。
 * run()
 * 当task不为空，执行任务，当task执行完毕，再从任务队列获取任务并执行，主要就是要不断的取寻找任务执行。任务执行完毕之后就将this（当前的worker）从线程池中移除掉。
 *
 * @Version 1.0
 */
public class MyThreadPool {

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        /**
         * //执行任务
         * //1)当task不为空，执行任务
         * //2)当task执行完毕，再接着从任务队列获取任务并执行
         */
        @Override
        public void run() {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println("正在执行" + task.toString() + "任务");
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            System.out.println("worker 被移出" + this.toString());
            workers.remove(this);
        }
    }
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private Set<Worker> workers = new CopyOnWriteArraySet<>();
    //1 核心线程数
    private int coreSize;
    //2 获取任务的超时时间
    private long timeout;

    //3 时间单位
    private TimeUnit timeUnit;

    //4 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public MyThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int maxCoreSize, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(maxCoreSize);
        this.rejectPolicy = rejectPolicy;
    }


    /**
     * //当任务数没有超过coreSize时，就直接交给worker对象执行
     * //如果任务数超过coreSize时，加入任务队列暂存
     * @param task
     */
    public void execute(Runnable task) {
        if (workers.size() < coreSize) {
            Worker worker = new Worker(task);
            System.out.println("新增加 worker " + worker.toString());
            workers.add(worker);
            worker.start();
        } else {
            //try to put in waitQueue
            taskQueue.tryPut(rejectPolicy, task);
        }
    }
}
