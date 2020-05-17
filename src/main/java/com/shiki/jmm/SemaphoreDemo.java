package com.shiki.jmm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/17 1:48
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟3个停车位
        //模拟6部汽车
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    ;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); //释放停车位
                }
            }, String.valueOf(i)).start();
        }
    }
}
