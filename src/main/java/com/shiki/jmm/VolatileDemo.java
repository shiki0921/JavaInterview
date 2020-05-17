package com.shiki.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/10 15:38
 */

class MyData{

//    int number = 0;

    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    //注意，此时number前面是加了volatile关键字修饰的
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile可见性
 *
 * 2.验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *       不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可用被加塞或者被分割，
 *       需要整体完整，要么同时成功，要么同时失败。
 *  2.2 volatile不保证原子性
 *
 *  2.3 why
 *
 *  2.4 如何解决原子性？
 *      * 加sync
 *      * juc atomic
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1;i<=20;i++){
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待20个线程计算完成后，再由main线程取得最终的结果值是多少
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int finally number value: "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t atomicInteger finally number value: "+myData.atomicInteger);
    }

    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        //第一个线程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value: "+myData.number);
        }, "AAA").start();

        //第二个线程就是main线程
        while (myData.number == 0){
            //main线程一直在这里循环等待，直到number不等于0
        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value: "+myData.number);
    }
}
