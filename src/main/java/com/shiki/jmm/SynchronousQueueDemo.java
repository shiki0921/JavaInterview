package com.shiki.jmm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shiki
 * @title: 阻塞队列SynchronousQueueDemo演示
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/17 15:13
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\tput 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\tput 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\tput 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
