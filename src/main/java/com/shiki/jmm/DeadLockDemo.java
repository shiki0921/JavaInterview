package com.shiki.jmm;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"尝试获取"+lockB);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockB+"尝试获取"+lockA);
            }
        }
    }

}

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/20 22:18
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBBB").start();

        /**
         * jps -l 查看进程号
         * jstack 进程号
         */
    }
}
