package com.thread.chapter09;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁案例，以及死锁检测解决（中断死锁线程）
 * Created by chenbin on 2019\8\16 0016.
 */
public class DeadLockDemo2 implements Runnable {
    public int lock;
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public DeadLockDemo2(int lock) {
        this.lock = lock;
    }

    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.print(Thread.currentThread().getId() +
                    ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockDemo2 r1 = new DeadLockDemo2(1);
        DeadLockDemo2 r2 = new DeadLockDemo2(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        Thread.sleep(1000);
    }
}
