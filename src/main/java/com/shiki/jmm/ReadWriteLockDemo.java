package com.shiki.jmm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{ //资源类
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成："+key);
        }finally {
            rwlock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
        }finally {
            rwlock.readLock().unlock();
        }
    }
}

/**
 * @author shiki
 * @title: 读写锁
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/13 22:34
 *
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该再有其他线程可以对改资源进行读或写
 * 总结：
 *          读-读能共存
 *          读-写不能共存
 *          写-写不能共存
 *
 *          写操作：原子+独占，中间整个过程必须是一个完整的统一体，中间不许被分割，不能被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"", tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
