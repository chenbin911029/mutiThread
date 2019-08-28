package com.thread.chapter07.producer.consumer;

/**
 * 生产者消费者，测试主函数
 * Created by chenbin on 2019\8\21 0021.
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);
        Consumer c = new Consumer(ss);

        new Thread(p).start();
        try {
            //主线程休眠一段时间，让生产线程先跑一会儿。
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(c).start();
    }
}
