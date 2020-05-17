package com.shiki.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/17 0:59
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*********秦帝国，一统华夏");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t关门");
    }
}
