package com.jvm.classLoader;

/**
 * 自底向上检查类是否加载，
 * 自顶向下尝试加载类
 * Created by chenbin on 2019\9\23 0023.
 */
public class SysClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            protected synchronized Class<?> loadClass(String name,boolean resolve) throws ClassNotFoundException {
                //首先，检查请求的类是否已经被加载过了
                Class c = findLoadedClass(name);
                if (c == null) {
/*                    try {
                        if (parent != null) {
                            c = parent.loadClass(name,false);
                        } else {
                            c = findBoostrapClassOrNull(name);
                        }
                    } catch (ClassNotFoundException e) {
                        *//**
                         * 如果父类加载器抛出ClassNotFoundException
                         * 说明父类加载器无法完成加载请求
                         *//*
                    }*/
                    if (c == null) {
                        /**
                         * 在父类加载器无法加载的时候
                         * 再调用本身的findClass方法来进行类加载
                         */
                        c = findClass(name);
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        };
    }
}
