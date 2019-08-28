package com.thread.chapter07.producer.consumer;

/**
 * 生产馒头
 * Created by chenbin on 2019\8\21 0021.
 */
public class Producer implements Runnable {
    SyncStack ss = null;

    Producer(SyncStack ss) {
        this.ss = ss;
    }

    public void run() {

        for (int i = 0;;i++) {
            ManTou wt = new ManTou(i);
            ss.push(wt);
            System.out.println("生产了：" + wt);
            try {
                //生产一个睡1秒，便于观察
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
