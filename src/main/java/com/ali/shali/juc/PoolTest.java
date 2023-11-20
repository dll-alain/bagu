package com.ali.shali.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author shali
 * @Date 2023/9/18 17:55
 * @PackageName:com.ali.shali.juc
 * @ClassName: PoolTest
 * @Description: TODO
 * @Version 1.0
 */
public class PoolTest {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(3, 1000, TimeUnit.MICROSECONDS, 10,
                (queue, task) -> {
                    //1、死等
                    //queue.put(task);
                    //2、带超时等待
                    queue.offer(task, 1500, TimeUnit.NANOSECONDS);
                    //3、放弃任务执行
                    // log.debug("放弃{}",task);
                    //4、抛出异常
                    // throw new RuntimeException("任务执行失败"+task);
                    //5、让调用者自己执行任务
                    // task.run();

                });

        for (int i = 0; i < 20; ++i) {
            int finalI = i;
            myThreadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(finalI);
            });
        }

    }
}
