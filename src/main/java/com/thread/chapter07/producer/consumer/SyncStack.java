package com.thread.chapter07.producer.consumer;

/**
 * 篮子（装馒头）
 * Created by chenbin on 2019\8\21 0021.
 */
public class SyncStack {
    int index = 0;
    //篮子只能装六个馒头
    ManTou[] arrMT = new ManTou[6];

    //装馒头
    public synchronized void push(ManTou wt) {
        while(index == arrMT.length) {
            //篮子满了，装动作等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        arrMT[index] = wt;
        index++;
    }

    //取馒头
    public synchronized ManTou pop() {
        while (index == 0) {
            //篮子空了，取动作等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        index--;
        return arrMT[index];
    }
}
