package com.thread.chapter06.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务，线程池使用demo
 * Created by chenbin on 2019\8\19 0019.
 */
public class SchduleExecutorServiceDemo {
    public static class MyTask implements Runnable {
        public void run() {
            System.out.println(System.currentTimeMillis() +
                    ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，则调度也不会启动
        ses.scheduleWithFixedDelay(task,0,2, TimeUnit.SECONDS);
    }
}
