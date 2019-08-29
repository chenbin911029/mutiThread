package com.thread.chapter09.jdk8;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 * 乐观读
 */
public class StampLockDemo3 {

    private int balance;

    private StampedLock lock = new StampedLock();
    public StampLockDemo3() {
        balance=10;
    }

    /**
     * 读的时候不阻塞写，乐观读
     * 通过判断 时间戳 来判断是否需要重读（其它线程对变量做了写操作的情况下，时间戳会发生改变）
     */
    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
        // 这里可能会出现了写操作，因此要进行判断
        if(!lock.validate(stamp)) {
            // 要重新读取
            System.out.println("要重新读取");
            stamp = lock.readLock();
            try{
                c = balance;
            }
            finally{
                lock.unlockRead(stamp);
            }
        }
        System.out.println("读取的值为:"+c);
    }

    /**
     * 写操作会获取写锁
     * @param value
     */
    public void write(int value) {
        long stamp = lock.writeLock();
        balance += value;
        lock.unlockWrite(stamp);
    }


    public static void main(String[] args) {
        StampLockDemo3 demo=new StampLockDemo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    demo.optimisticRead();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    demo.write(2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
