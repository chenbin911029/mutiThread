package com.jvm.jvm02;

/**
 * Created by chenbin on 2019\8\30 0030.
 */
public class OnStackTest {

    public static void alloc(){
        byte[] b = new byte[2];
        b[0]=1;
    }

    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }

    /**
     * 没有配置 jvm参数时，输出:1402
     * 配置后
     * -server -Xmx10m -Xms10m
     * -XX:-DoEscapeAnalysis -XX:+PrintGC
     *
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003113 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003287 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003761 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003236 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003284 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003357 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003761 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003306 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003280 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003608 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003189 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003233 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003123 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0002959 secs]
     [GC (Allocation Failure)  2764K->716K(9728K), 0.0003572 secs]
     3426
     */
}
