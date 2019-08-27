package com.thread.chapter06.simple;

/**
 * 简单线程池的使用测试
 * Created by chenbin on 2019\8\27 0027.
 */
public class SimpleThreadPoolTest {

    //自定义任务
    public static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("正在执行，"
                    + System.currentTimeMillis()
                    + ":Thread ID:" + Thread.currentThread().getId()
                    + "，Task Name=" + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool pool = SimpleThreadPool.getInstance();
        //使用线程池来执行100个任务
        for (int i = 0; i < 100; i++) {
            MyTask task = new MyTask("task-name-"+i);
            pool.start(task);
        }
    }

}
