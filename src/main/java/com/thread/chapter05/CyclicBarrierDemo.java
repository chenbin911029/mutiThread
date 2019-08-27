package com.thread.chapter05;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * Created by chenbin on 2019\8\16 0016.
 */
public class CyclicBarrierDemo {
    //每个线程的任务
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        Soldier(CyclicBarrier cyclicBarrier,String soldier) {
            this.cyclicBarrier = cyclicBarrier;
            this.soldier = soldier;
        }

        public void run() {
            try {
                //等待栅栏位满
                cyclicBarrier.await();
                doWork();
                //等待栅栏所有线程工作结束
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }finally {

            }
        }

        //
        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "任务完成");
        }
    }

    //栅栏位满后需要执行的动作
    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag,int N) {
            this.flag = flag;
            this.N = N;
        }

        public void run() {
            if (flag) {
                System.out.println(N +"个士兵任务完成。");
            } else {
                System.out.println(N +"个士兵集合和完毕");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));

        //设置屏障点
        System.out.println("集合队伍。");
        for (int i = 0;i < N; ++i) {
            System.out.println("士兵"+i+"报道。");
            allSoldier[i] = new Thread(new Soldier(cyclic,"士兵"+i));
            allSoldier[i].start();
//            if (i == 5) {
//                allSoldier[i].interrupt();
//            }
        }

    }
}
