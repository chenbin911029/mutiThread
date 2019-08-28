package com.thread.chapter07.patterns.future;

/**
 * 获取结果接口
 * Created by chenbin on 2019\8\21 0021.
 */
public interface Data {
    String getResult() throws InterruptedException;
}
