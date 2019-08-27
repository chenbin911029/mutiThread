package com.thread.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，条件 的使用
 * Created by chenbin on 2019\8\16 0016.
 */
public class ReenterConditionLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.print("Thread is going on.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterConditionLock r1 = new ReenterConditionLock();
        Thread t1 = new Thread(r1);
        t1.start();

        Thread.sleep(2000);
        //通知t1继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
