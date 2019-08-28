package com.thread.chapter07.patterns.future;

/**
 * Created by chenbin on 2019\8\21 0021.
 */
public class RealData implements Data {
    private String data;

    public RealData(String data) {
        //利用sleep方法表示RealData构造过程缓慢
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    public String getResult() {
        return data;
    }
}
