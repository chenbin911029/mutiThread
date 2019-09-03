package com.jvm.jvm03;

import java.util.Vector;

/**
 * -XX:+HeapDumpOnOutOfMemoryError
 * OOM时导出堆到文件
 * -XX:+HeapDumpPath
 * 导出OOM的路径 E:/programming/projects/mutiThread/target/a.dump
 *
 * -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=E:/programming/projects/mutiThread/target/a.dump
 * Created by chenbin on 2019\9\3 0003.
 */
public class DumpPath {

    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i=0;i<25;i++)
            v.add(new byte[1*1024*1024]);
    }

    /**
     * 在OOM时，执行一个脚本
      "-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p“
      当程序OOM时，在D:/a.txt中将会生成线程的dump
      可以在OOM时，发送邮件，甚至是重启程序
     */

    /**
     * 根据实际事情调整新生代和幸存代的大小
       官方推荐新生代占堆的3/8
       幸存代占新生代的1/10
       在OOM时，记得Dump出堆，确保可以排查现场问题
     */
}
