package com.shiki.jmm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author shiki
 * @title:
 * @projectName JavaInterview
 * @description: TODO
 * @date 2020/5/12 21:33
 */

@Getter
@ToString
@AllArgsConstructor
class User{
    String name;
    int age;
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 24);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());
    }
}
