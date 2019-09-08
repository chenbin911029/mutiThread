package com.jvm.gc;

/**
 * Created by chenbin on 2019\9\7 0007.
 */
public class testAllocation {
    private static final int _1MB = 1024*1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     */
    public static void allocation() {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];
    }

    /**
     * --verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728  超过这个值的对象直接进入老年代
     */
    public static void allocation2() {
        byte[] allocation = new byte[4 * _1MB];
    }

    /**
     * --verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1
     */
    public static void allocation3() {
        byte[] allocation1 = new byte[_1MB/4];
        byte[] allocation2 = new byte[4 * _1MB];
        allocation2 = null;
        allocation2 = new byte[4 * _1MB];
        byte[] allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        //allocation();
        //allocation2();
        allocation3();
    }
}
