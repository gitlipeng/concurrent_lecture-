package com.concurrent.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        atomicInteger.incrementAndGet();

    }
}
