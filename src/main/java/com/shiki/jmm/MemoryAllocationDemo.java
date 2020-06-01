package com.shiki.jmm;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/31 12:40
 */
public class MemoryAllocationDemo {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //testAllocation();
        testPretenureSizeThreshold();
    }

    /**
     * VM参数: -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  //出现一次Minor GC
    }

    /**
     * 大对象直接进入老年代
     * VM参数: -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation = new byte[4 * _1MB];
    }
}
