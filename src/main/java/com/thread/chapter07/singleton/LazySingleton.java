package com.thread.chapter07.singleton;

/**
 * Created by chenbin on 2019\8\28 0028.
 */
//延迟加载
public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton is create");
    }
    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
    }
}

