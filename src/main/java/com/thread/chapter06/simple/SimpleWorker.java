package com.thread.chapter06.simple;

/**
 * 简单线程池实现
 * Created by chenbin on 2019\8\19 0019.
 */
public class SimpleWorker extends Thread {
    //线程池
    private SimpleThreadPool pool;
    //任务
    private Runnable target;
    private boolean isShutDown = false;
    private boolean isIdle = false;

    public SimpleWorker(Runnable target, String name, SimpleThreadPool pool) {
        super(name);
        this.pool = pool;
        this.target = target;
    }

    public Runnable getTarget() {
        return target;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void run() {
        while(!isShutDown) {
            isIdle = false;
            if (target != null) {
                //运行任务
                target.run();
            }
            //任务结束了
            isIdle = true;
            try {
                pool.repool(this);
                synchronized (this) {
                    //线程空闲，等待新的任务到来
                    wait();
                }
            } catch (InterruptedException e) {

            }
            isIdle = false;
        }
    }

    public synchronized void setTarget(Runnable newTarget) {
        target = newTarget;
        //设置任务之后，通知run方法，开始执行这个任务
        notifyAll();
    }

    public synchronized void shutdown() {
        isShutDown = true;
        notifyAll();
    }
}
