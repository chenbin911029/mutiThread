package com.thread.ch01.volatile1;

import com.thread.ch01.tools.SleepTools;

/**
 * volatile 保证线程可见性，没有原子性
 */
public class VolatileUnsafe {
    private static class VolatileVar implements Runnable {
        private volatile int a = 0;

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            a += 1;
            System.out.println(threadName+":======"+a);
            SleepTools.ms(100);
            a = a+1;
            System.out.println(threadName+":======"+a);
        }
    }

    public static void main(String[] args) {
        VolatileVar v = new VolatileVar();
        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
