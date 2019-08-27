package com.thread.chapter05;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by chenbin on 2019\8\16 0016.
 */
public class LockSupportDemo {
    public static Object u  = new Object();
    
    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in "+getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread("t1");
        ChangeObjectThread t2 = new ChangeObjectThread("t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}
