package com.jvm.jvm02;

/**
 * String常量分配的测试
 * jdk1.8 String 常量分配在堆中的常量池
 * Created by chenbin on 2019\9\2 0002.
 */
public class StringTest {

    public static void main(String[] args) {
        /**
         * 两个对象（如果原来常量池中没有"string"）
         * String str2 = new String("string"); 创建实例的过程
         * 1.首先在堆中（不是常量池）创建一个指定的对象"string",并让str2引用指向该对象
         * 2.在字符串常量池中查看，是否存在内容为"string"字符串对象
         * 3.若存在，则将new出来的字符串对象与字符串常量池中的对象联系起来
         * 4.若不存在，则在字符串常量池中创建一个"string"的字符串对象，并将堆中的对象与之联系起来
         */
        String str2 = new String("string");
        /**
         * 1.首先在常量池中查找是否存在内容为"abc"字符串对象
         * 2.如果不存在则在常量池中创建"abc"，并让str引用该对象
         * 3.如果存在则直接让str引用该对象
         */
        String str1 = "string";
        String str3 = "string";

        /**
         * == 比较地址值
         * equals()比较内容
         * str2 new的对象在堆中
         * str1 所引用的字符串内容是在字符串常量池中
         * 所以str2 的地址 和 str1的地址不同
         */
        System.out.println(str1 == str2);
        /**
         * intern生成的str2所引用的字符串内容是在字符串常量池中，
         * 而通过new String方法生成的str1，
         * 该字符串对象是位于存放对象的Java堆中，二者的地址是不同的
         */
        System.out.println(str2.intern() == str2);
        /**
         * 1.首先在常量池中查找是否存在内容为"abc"字符串对象
         * 2.如果不存在则在常量池中创建"abc"，并让str引用该对象
         * 3.如果存在则直接让str引用该对象
         *   所以 str1 ,str3 指向的是同一个地址
         */
        System.out.println(str3 == str1);
    }
}
