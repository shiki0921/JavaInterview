package com.shiki.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t #########invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        //两两配对，加几次锁就要释放几次锁
        lock.lock();
        //lock.lock();
        try{
            //线程可以进入任何一个它已经拥有的锁所同步着的代码块
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
            //lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t #######invoked set()");
        }finally {
            lock.unlock();
        }
    }
}


/**
 * @author shiki
 * @title: 可重入锁(也叫递归锁)
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/13 21:17
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 * case one synchronized
 * t1	 invoked sendSMS()   t1线程外层函数获得锁
 * t1	 #########invoked sendEmail()  进入内层方法会自动获取锁
 *
 * t2	 invoked sendSMS()
 * t2	 #########invoked sendEmail()
 *
 * case two ReentrantLock
 *      典型的可重入锁
 */
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSMS();
        }, "t1").start();

        new Thread(()->{
            phone.sendSMS();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================");
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();
    }
}
