package com.thread.chapter06.simple;

import java.util.List;
import java.util.Vector;

/**
 * 简单线程池实现
 * Created by chenbin on 2019\8\19 0019.
 */
public class SimpleThreadPool {
    private static SimpleThreadPool instance = null;

    //空闲的线程队列
    private List<SimpleWorker> idleThreads;
    //已有线程总数
    private int threadCounter;
    private boolean isShutDown = false;

    private SimpleThreadPool() {
        this.idleThreads = new Vector<SimpleWorker>(5);
        threadCounter = 0;
    }

    public int getCreatedThreadCount() {
        return threadCounter;
    }

    //获取线程池的实例
    public synchronized static SimpleThreadPool getInstance() {
        if (instance == null) {
            instance = new SimpleThreadPool();
        }
        return instance;
    }

    //将线程放入池中
    protected synchronized void repool(SimpleWorker repoolingThread){
        if (!isShutDown) {
            idleThreads.add(repoolingThread);
        } else {
            repoolingThread.shutdown();
        }
    }

    //停止池中的所有线程
    public synchronized void shutdown() {
        isShutDown = true;
        for (int threadIndex = 0;threadIndex < idleThreads.size();threadIndex++) {
            SimpleWorker idleThread = (SimpleWorker) idleThreads.get(threadIndex);
            idleThread.shutdown();
        }
    }

    //执行任务
    public synchronized void start(Runnable target) {
        SimpleWorker thread = null;
        //如果有空闲线程，则直接使用
        if (idleThreads.size() > 0) {
            int lastIndex = idleThreads.size() -1;
            thread = (SimpleWorker) idleThreads.get(lastIndex);
            //立即执行
            thread.setTarget(target);
            idleThreads.remove(lastIndex);
        } else {
            //没有空闲线程，则创建新线程
            threadCounter++;
            thread = new SimpleWorker(target,"pThreaed #"+threadCounter,this);
            //启动这个线程Worker
            thread.start();
        }
    }
}
