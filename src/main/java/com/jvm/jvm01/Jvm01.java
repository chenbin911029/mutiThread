package com.jvm.jvm01;

/**
 * 整数的表达:
 * 原码：第一位为符号位（0为正数，1为负数）
 * 反码：符号位不动，原码取反
 * 负数补码：符号位不动，反码加1
 * 正数补码：和原码相同
 *
 * 用补码来表示的原因：在运算的时候，两个补码相加即可得到结果。
 * Created by chenbin on 2019\8\30 0030.
 */
public class Jvm01 {

    public static void main(String[] args) {
        int a=-6;
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i)>>>(31-i);
            System.out.print(t);
        }
    }
}
