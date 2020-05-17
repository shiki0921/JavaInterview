package com.shiki.jmm;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/11 16:10
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1  CAS是什么
 *      比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,10 )+"\t current data: "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,100 )+"\t current data: "+atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
