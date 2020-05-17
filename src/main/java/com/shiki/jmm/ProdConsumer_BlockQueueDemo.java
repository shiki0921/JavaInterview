package com.shiki.jmm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"插入队列:"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"插入队列:"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"大老板叫停了，表示FLAG=false，生产动作结束");
    }
    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")){
                FLAG = false;
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费队列:"+result+"成功");
        }
    }
    public void stop(){
        this.FLAG = false;
    }
}

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/17 23:45
 *
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
    }
}
