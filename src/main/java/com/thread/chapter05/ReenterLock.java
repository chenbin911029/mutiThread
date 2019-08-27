package com.thread.chapter05;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * Created by chenbin on 2019\8\16 0016.
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for (int j = 0;j < 10000000;j++) {
            //访问共享数据 i 要加锁
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock r1 = new ReenterLock();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.print(i);
    }
}
