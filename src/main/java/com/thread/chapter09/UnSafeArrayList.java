package com.thread.chapter09;

import java.util.ArrayList;

/**
 * t1,t2同时对 arrayList进行操作，会出现脏读，脏写
 * 数组越界异常是因为 size在被扩容之前，比如 t1,t2都
 * 读到了size = 9;然后都认为可以继续做 add 操作，t1把
 * size++ 到长度为10，t2仍然以为size = 9，可以add，结果
 * size++ 为11，就发生了越界访问。
 * Created by chenbin on 2019\8\24 0024.
 */
public class UnSafeArrayList {
    static ArrayList al = new ArrayList();
    static class AddTask implements Runnable {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            for (int i = 0;i < 1000000;i++) {
                al.add(new Object());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddTask(),"t1");
        Thread t2 = new Thread(new AddTask(),"t2");

        t1.start();
        t2.start();

    }
}
