package com.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Java方法区溢出
 * 借助cglib字节码技术。
 * spring,hibernate在对类增强时都会使用到cglib.
 * 类越多越需要大的方法区来保证动态的calss可以加载入内存。
 *
 *  -verbose:gc -XX:+PrintGCDetails -XX:PermSize=128k -XX:MaxPermSize=128k
 * Created by chenbin on 2019\9\1 0002.
 */
public class JavaMethodAreaOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,args);
                }
            });
            enhancer.create();

        }
        /**
         * 生成大量动态类:Java方法区内存溢出
         * Java.lang.OutOfMemoryError:PermGen space
         */
    }
}
