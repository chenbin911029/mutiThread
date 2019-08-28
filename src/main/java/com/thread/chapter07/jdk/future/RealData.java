package com.thread.chapter07.jdk.future;

import java.util.concurrent.Callable;

/**
 * future模式另起线程调用执行这个任务，
 * 这个任务计算自己定制的业务逻辑。
 * Created by chenbin on 2019\8\21 0021.
 */
public class RealData implements Callable<String> {
    protected String data;

    public RealData(String data) {
        this.data = data;
    }

    public String call() throws Exception {
        //利用sleep方法来表示真实业务非常缓慢
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
}
