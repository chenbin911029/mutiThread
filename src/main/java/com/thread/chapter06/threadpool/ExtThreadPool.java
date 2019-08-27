package com.thread.chapter06.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展和增强线程池demo
 * Created by chenbin on 2019\8\19 0019.
 */
public class ExtThreadPool {
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成：" + ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出。");
            }
        };

        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask("task-name-"+i);
            es.execute(task);
        }

        es.shutdown();
    }
}
