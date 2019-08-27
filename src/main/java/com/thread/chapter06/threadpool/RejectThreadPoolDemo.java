package com.thread.chapter06.threadpool;

import java.util.concurrent.*;

/**
 * 线程池，自定义拒绝策略
 * Created by chenbin on 2019\8\19 0019.
 */
public class RejectThreadPoolDemo {
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 所定义的线程池没有阻塞队列，即：超过最大线程数后，剩余的线程会被拒绝执行
         * 这里的拒绝策略是：忽略被拒绝的线程
         */
        ExecutorService es = new ThreadPoolExecutor(5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+"is discard.");
                    }
                });

        for (int i = 0; i < 100; i++) {
            MyTask task = new MyTask("task-name-"+i);
            es.execute(task);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        es.shutdown();
    }
}
