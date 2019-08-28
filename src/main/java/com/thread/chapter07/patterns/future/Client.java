package com.thread.chapter07.patterns.future;

/**
 * Created by chenbin on 2019\8\21 0021.
 */
public class Client {
    public Data request(final String str) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            public void run() {
                //RealData构造缓慢，所以放在单独的线程中运行
                RealData realData = new RealData(str);
                futureData.setRealData(realData);
            }
        }).start();

        //先直接返回FutureData
        return futureData;
    }
}
