package com.thread.simple;

/**
 * 线程阻塞与唤醒
 * Created by chenbin on 2019\8\25 0025.
 */
public class SyncThread {
    private static final Object object = new Object();

    //t1线程执行，被object.wait()阻塞
    static class T1 extends Thread{
        public void run(){
            synchronized (object) {
                System.out.println(System.currentTimeMillis()+":T1 start! ");
                try {
                    System.out.println(System.currentTimeMillis()
                            +":T1 wait for object ");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end!");
            }
        }
    }

    //t2线程执行，object.notify()唤醒t1线程
    static  class T2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis()
                        + ":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        t2.start();
    }
}
