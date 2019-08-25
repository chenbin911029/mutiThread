package com.thread.simple;

/**
 * Created by chenbin on 2019\8\25 0025.
 */
public class SimpleThread {

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello, I am task1");
        }
    }

    public static void main(String[] args) {
        //创建线程方法一：创建Thread,覆写run()方法
        Thread t1 = new Thread(){
            @Override
            public void run(){
                System.out.println("Hello, I am t1");
            }
        };
        t1.start();

        //创建线程方法二：实现Runnable接口，实现run()方法
        Thread t2 = new Thread(new MyTask());
        t2.start();

        //守护线程
        Thread t3 = new Thread();
        t3.setDaemon(true);
        t3.start();

        //线程优先级设置
        Thread high= new Thread();
        Thread low= new Thread();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
