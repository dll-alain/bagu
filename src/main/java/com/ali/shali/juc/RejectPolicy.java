package com.ali.shali.juc;

/**
 * @Author shali
 * @Date 2023/9/18 17:35
 * @PackageName:com.ali.shali.juc
 * @ClassName: RejectPolicy
 * @Description: 拒绝策略
 * @Version 1.0
 */
@FunctionalInterface
public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
