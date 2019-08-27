package com.thread.chapter05.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁案例，以及死锁检测解决（中断死锁线程）
 * Created by chenbin on 2019\8\16 0016.
 */
public class DeadLockDemo implements Runnable {
    public int lock;
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public DeadLockDemo(int lock) {
        this.lock = lock;
    }

    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
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
        DeadLockDemo r1 = new DeadLockDemo(1);
        DeadLockDemo r2 = new DeadLockDemo(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        Thread.sleep(1000);
        //下面代码注释后，就是死锁案例。这个方法可以检测死锁线程，并且中断进入死锁的线程。
        DeadlockChecker.check();
    }
}
