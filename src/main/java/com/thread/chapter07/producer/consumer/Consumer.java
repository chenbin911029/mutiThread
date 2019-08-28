package com.thread.chapter07.producer.consumer;

/**
 * 消费馒头
 * Created by chenbin on 2019\8\21 0021.
 */
public class Consumer implements Runnable {
    SyncStack ss = null;

    Consumer(SyncStack ss) {
        this.ss = ss;
    }

    public void run() {
        for (;;) {
            ManTou wt = ss.pop();
            System.out.println("消费了：" + wt);
        }
    }
}
