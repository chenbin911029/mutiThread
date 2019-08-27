package com.thread.chapter05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  信号量的使用
 * Created by chenbin on 2019\8\16 0016.
 */
public class SemapDemo implements Runnable {
    public static Semaphore semaphore = new Semaphore(5);

    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() +
                    "done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i=0;i<20;i++) {
            exec.submit(demo);
        }
        exec.shutdown();
    }
}
