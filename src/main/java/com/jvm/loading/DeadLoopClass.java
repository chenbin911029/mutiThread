package com.jvm.loading;

/**
 * Created by chenbin on 2019\9\23 0023.
 */
public class DeadLoopClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass.");
            while(true) {

            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + "run over");
            }
        };

        Thread t1 = new Thread(script);
        Thread t2 = new Thread(script);
        t1.start();
        t2.start();
    }
}
