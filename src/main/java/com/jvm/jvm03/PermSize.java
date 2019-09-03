package com.jvm.jvm03;

import net.sf.cglib.beans.BeanMap;

/**
 * 永久区分配参数
 * -XX:PermSize=1M  -XX:MaxPermSize=1M
 * -XX:PermSize 永久区的初始空间
 * -XX:MaxPermSize 最大空间
 * Created by chenbin on 2019\9\3 0003.
 */
public class PermSize {
    static class OOMObject{
        private String name = "name";

        public OOMObject(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        for (int i = 0;i < Integer.MAX_VALUE;i++) {
            BeanMap beanMap = BeanMap.create(OOMObject.class);
        }
    }

    /**
     *  -Xss
        通常只有几百K
        决定了函数调用的深度
        每个线程都有独立的栈空间
        局部变量、参数 分配在栈上
     */
}
