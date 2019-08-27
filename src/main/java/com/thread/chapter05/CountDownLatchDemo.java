package com.thread.chapter05;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒数计时器
 * Created by chenbin on 2019\8\16 0016.
 */
public class CountDownLatchDemo implements Runnable {
    public static CountDownLatch end = new CountDownLatch(10);

    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check done.");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final CountDownLatchDemo demo = new CountDownLatchDemo();
        for (int i = 0;i < 10;i++) {
            exec.submit(demo);
        }
        //等待检查
        end.await();
        System.out.println("Fire !");
        exec.shutdown();
    }
}
