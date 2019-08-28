package com.thread.chapter07.patterns.future;

/**
 * isReady=false，未得到真实结果前调用getResult()会被等待阻塞；
 * 当setRealData(realData)之后，会唤醒getResult()返回真实结果。
 * Created by chenbin on 2019\8\21 0021.
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public synchronized String getResult() {
        while(!isReady) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
