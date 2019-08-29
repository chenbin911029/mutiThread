package com.thread.chapter09.jdk8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * jdk8对并发的新支持
 * future模式
 * Created by chenbin on 2019\8\24 0024.
 */
public class CompletableFutureDemo {
    public static Integer calc(int para) {
        try {
            //模拟一个长时间执行
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        return para * para;
    }

    public static void main(String[] args) {
        //1.耗时操作，单独起一个异步线程来计算
        final CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(()-> calc(50));
        //2.其它操作在主线程中继续
        System.out.println("step 2.");
        System.out.println("step 3.");
        System.out.println("step 4.");
        /**
         * 3.最后获取第一步耗时操作的计算结果，若2完成后1仍未完成，则会等到1完成后
         * 返回结果
         */
        try {
            System.out.println("获取future的计算结果：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
