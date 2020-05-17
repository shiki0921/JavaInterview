package com.shiki.jmm;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/11 15:25
 */
public class SingletonDemo {

    //private static SingletonDemo instance = null;

    private static volatile SingletonDemo instance;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t come in");
    }

    //DCL(Double Check Lock双端检锁机制)
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //并发
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();;
        }
    }
}
