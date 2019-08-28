package com.thread.chapter07.patterns.future;

/**
 * future模式测试main函数
 * Created by chenbin on 2019\8\21 0021.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        //这里立即返回，因为获取的是FutureData,而非RealData
        Data data = client.request("name");
        //这里可以使用sleep代替对其它业务逻辑的处理
        //在其它业务逻辑处理的过程中，RealData也正在创建，从而充分利用等待时间
        Thread.sleep(2000);
        //等待，直到获得真实数据结果
        System.out.println("返回的真实数据="+data.getResult());
    }
}
