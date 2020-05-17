package com.shiki.jmm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author shiki
 * @title: 阻塞队列
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/17 14:20
 *
 * 1.队列
 *
 * 2.阻塞队列
 *    2.1 阻塞队列有没有好的一面
 *
 *    2.2 不得不阻塞，如何管理
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }
}
