package com.jvm.param;

/**
 * 堆的分配参数，查看
 * Created by chenbin on 2019\9\3 0003.
 */
public class JvmParam {
    /**
     * -Xms5M java堆初始空间
     * -Xmx20M Java堆最大值
     * -Xmn3M 设置新生代大小
     *
     * -XX:NewRatio=4
     * 新生代（eden+2*s）和老年代（不包含永久区）的比值
     * 4表示新生代:老年代=1:4，即年轻代占堆的1/5
     *
     * -XX:SurvivorRatio=8
     * 设置两个Survivor区和eden的比
     * 8 表示两个Survivor:eden=2:8，即一个Survivor占年轻代的1/10
     */
    public static void main(String[] args) {
        byte[] b = new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");

        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024 + "M");
        /**
         * Java会尽可能维持在最小堆
         */
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024 + "M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024 + "M");

        /**
         * 分配了4M空间给数组
         Xmx=18.0M
         free mem=5.001502990722656M
         total mem=10.0M
         这里堆空间自动扩展了
         */

        System.gc();
        /**
         * 调用gc空闲内存增多了
         */
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024 + "M");

        System.out.println("调用gc空闲内存增多了");
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024 + "M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024 + "M");
    }
}
