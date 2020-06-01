package com.shiki.jmm;

import java.lang.ref.WeakReference;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/27 22:09
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
    }
}
