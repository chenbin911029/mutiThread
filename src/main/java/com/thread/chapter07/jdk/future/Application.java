package com.thread.chapter07.jdk.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 使用JDK内置的futureTask
 * Created by chenbin on 2019\8\21 0021.
 */
public class Application {
    public static void main(String[] args) throws Exception {
        Callable<String> callable = new RealData("jdk-future-mode");
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        //使用线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //执行futureTask
        executor.submit(futureTask);
        //这里可以用一个sleep代替对其他业务逻辑的处理
        //在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间
        Thread.sleep(2000);
        //使用真实数据
        //如果call()方法没有执行完成，依然会等待
        System.out.println("使用JDK内置的futureTask="+futureTask.get());
    }
}
