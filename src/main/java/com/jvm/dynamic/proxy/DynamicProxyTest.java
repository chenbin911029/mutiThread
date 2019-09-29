package com.jvm.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenbin on 2019\9\29 0029.
 */
public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world.");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        /**
         * 传入目标对象
         * @param originalObj
         * @return 代理对象
         */
        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }

        /**
         * 可以对代理对象做增强操作
         * @param proxy 代理对象
         * @param method 需要增强的目标方法
         * @param args 目标方法参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                //执行目标方法前，before
                System.out.println("before method.");
                Object obj = method.invoke(originalObj,args);
                //执行目标方法后，after
                System.out.println("after method.");
                return obj;
            } catch (Exception e) {
                //抛出异常
                System.out.println("Exception happen,err.");
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
