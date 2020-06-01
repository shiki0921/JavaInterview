package com.shiki.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/31 16:00
 */
public class MonitorTest {

    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void main(String[] args) throws Exception{
        fillHeap(1000);
    }

    public static  void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}
