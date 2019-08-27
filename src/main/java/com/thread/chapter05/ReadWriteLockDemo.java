package com.thread.chapter05;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁使用场景：
 * 当读操作远大于写，则读写锁可以发挥很大作用。
 * 这段代码使用读写锁，程序大约两秒就可以运行完成，而使用注释中的重入锁，则需要20秒才可以运行完成。
 * Created by chenbin on 2019\8\27 0027.
 */
public class ReadWriteLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.readLock();
    private int value;

    //读方法
    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    //写方法
    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    //测试main()函数
    public static void main(String[] args) {
        final ReadWriteLockDemo exp = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    exp.handleRead(readLock);
//                    exp.handleRead(lock);
                    System.out.println("readRunnable线程执行当前时间戳：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    exp.handleWrite(writeLock, new Random().nextInt());
//                    exp.handleWrite(lock, new Random().nextInt());
                    System.out.println("writeRunnable线程执行当前时间戳：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //18个线程读数据
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        //2个线程写数据
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }

}
