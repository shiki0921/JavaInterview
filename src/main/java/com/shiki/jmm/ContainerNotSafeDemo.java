package com.shiki.jmm;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author shiki
 * @title: 集合类线程不安全的问题
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/12 22:12
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        //java.util.ConcurrentModificationException

        /**
         * 1 故障现象
         *      java.util.ConcurrentModificationException
         * 2 导致原因
         *
         * 3 解决方案
         *     3.1 new Vector<>()
         *     3.2 Collections.synchronizedList(new ArrayList<>())
         *     3.3 new CopyOnWriteArrayList();
         *
         * 4 优化建立
         */}
}
