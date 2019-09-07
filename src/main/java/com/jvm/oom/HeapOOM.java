package com.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * 该示例需要设置JVM参数：
 * -verbose:gc -Xms20M -Xmx20M  -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=6
 *
 * 堆的最小值 -Xms 堆和最大值 -Xmx 设置成一样可以避免自动扩展
 * -Xms20M 初始化堆内存大小(最小空间)
   -Xmx20M 堆内存最大值
   -Xmn10M 设置年轻代大小为10M。
   -Xss:设置每个线程的堆栈大小
   -XX:+PrintGcDetails 用于打印GC的日志信息
   -verbose:gc 用于查看Java垃圾收集的结果

   -XX:SurvivorRatio=6 ，设置的是Eden区与每一个Survivor区的比值，可以反推出占新生代的比值，
    Eden为6, 两个Survivor为2, Eden占新生代的3/4, 每个Survivor占1/8，两个占1/4
 * Created by chenbin on 2019\9\1 0001.
 */
public class HeapOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
