package com.thread.chapter07.producer.consumer;

/**
 * 馒头
 * Created by chenbin on 2019\8\21 0021.
 */
public class ManTou {
    int id;
    ManTou(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ManTou: " + id;
    }
}
