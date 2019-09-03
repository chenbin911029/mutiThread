package com.jvm.jvm03;

/**
 * 数组的空间分配，老年代
 * Created by chenbin on 2019\9\3 0003.
 */
public class ArrayAlloc {

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
        byte[] b = null;
        for(int i=0;i<10;i++)
            b = new byte[1*1024*1024];
    }
    /**
     * -Xmx20m -Xms20m -Xmn1m  -XX:+PrintGCDetails
     * 1.没有触发GC
     * 2.全部分配在老年代
     */
    /**
     *
     Heap
     PSYoungGen      total 1024K, used 972K [0x00000000ffe80000, 0x0000000100000000, 0x0000000100000000)
     eden space 512K, 91% used [0x00000000ffe80000,0x00000000ffef5008,0x00000000fff00000)
     from space 512K, 98% used [0x00000000fff00000,0x00000000fff7e030,0x00000000fff80000)
     to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     ParOldGen       total 18944K, used 10240K [0x00000000fec00000, 0x00000000ffe80000, 0x00000000ffe80000)
     object space 18944K, 54% used [0x00000000fec00000,0x00000000ff6000a0,0x00000000ffe80000)
     Metaspace       used 2873K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 308K, capacity 386K, committed 512K, reserved 1048576K
     Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
     */

    /**
     * jvm设置修改如下
     * -Xmx20m -Xms20m -Xmn15m  -XX:+PrintGCDetails
     * 1.没有触发GC
     * 2.全部分配在eden
     * 3.老年代没有使用
     */

    /**
     Heap
     PSYoungGen      total 13824K, used 12017K [0x00000000ff100000, 0x0000000100000000, 0x0000000100000000)
     eden space 12288K, 97% used [0x00000000ff100000,0x00000000ffcbc618,0x00000000ffd00000)
     from space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     to   space 1536K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x00000000ffe80000)
     ParOldGen       total 5120K, used 0K [0x00000000fec00000, 0x00000000ff100000, 0x00000000ff100000)
     object space 5120K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff100000)
     Metaspace       used 2873K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 308K, capacity 386K, committed 512K, reserved 1048576K
     */

    /**
     * jvm设置如下：
     * -Xmx20m -Xms20m -Xmn6m  -XX:+PrintGCDetails
     * 1.进行了2次新生代GC
     * 2.s0 s1 太小需要老年代担保
     */
    /**
     [GC (Allocation Failure) [PSYoungGen: 4215K->488K(5632K)] 4215K->1656K(19968K), 0.0017407 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [PSYoungGen: 4682K->480K(5632K)] 5850K->2772K(19968K), 0.0014786 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     PSYoungGen      total 5632K, used 3694K [0x00000000ffa00000, 0x0000000100000000, 0x0000000100000000)
     eden space 5120K, 62% used [0x00000000ffa00000,0x00000000ffd23b18,0x00000000fff00000)
     from space 512K, 93% used [0x00000000fff80000,0x00000000ffff8030,0x0000000100000000)
     to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
     ParOldGen       total 14336K, used 2292K [0x00000000fec00000, 0x00000000ffa00000, 0x00000000ffa00000)
     object space 14336K, 15% used [0x00000000fec00000,0x00000000fee3d030,0x00000000ffa00000)
     Metaspace       used 2874K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 308K, capacity 386K, committed 512K, reserved 1048576K
     */

    /**
     * -Xmx20m -Xms20m -Xmn6m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
     * -XX:SurvivorRatio=2
     * survivor:eden = 2:2
     * 进行了5次GC
     */
    /**
     [GC (Allocation Failure) [PSYoungGen: 2117K->1528K(4608K)] 2117K->1680K(18944K), 0.0018180 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [PSYoungGen: 3634K->1528K(4608K)] 3786K->1704K(18944K), 0.0016150 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [PSYoungGen: 3624K->1528K(4608K)] 3800K->1712K(18944K), 0.0008765 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [PSYoungGen: 3628K->1528K(4608K)] 3812K->1712K(18944K), 0.0018475 secs] [Times: user=0.08 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [PSYoungGen: 3631K->1520K(4608K)] 3815K->1748K(18944K), 0.0051561 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     PSYoungGen      total 4608K, used 2605K [0x00000000ffa00000, 0x0000000100000000, 0x0000000100000000)
     eden space 3072K, 35% used [0x00000000ffa00000,0x00000000ffb0f748,0x00000000ffd00000)
     from space 1536K, 98% used [0x00000000ffd00000,0x00000000ffe7c050,0x00000000ffe80000)
     to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     ParOldGen       total 14336K, used 228K [0x00000000fec00000, 0x00000000ffa00000, 0x00000000ffa00000)
     object space 14336K, 1% used [0x00000000fec00000,0x00000000fec39020,0x00000000ffa00000)
     Metaspace       used 2874K, capacity 4486K, committed 4864K, reserved 1056768K
     class space    used 308K, capacity 386K, committed 512K, reserved 1048576K
     */

    /**
     * -Xmx20m -Xms20m -XX:NewRatio=1
     * -XX:SurvivorRatio=2 -XX:+PrintGCDetails
     */

}
