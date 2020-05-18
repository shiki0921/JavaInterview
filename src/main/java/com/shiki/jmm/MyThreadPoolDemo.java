package com.shiki.jmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/18 22:38
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //池子有5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //池子有1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //池子N个处理线程

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
                TimeUnit.MILLISECONDS.sleep(200);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
