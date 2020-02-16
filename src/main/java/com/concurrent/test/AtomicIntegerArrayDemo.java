package com.concurrent.test;


import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);
    static Integer[] arr1 = new Integer[10];

    public static class AddThread implements Runnable{
        @Override
        public void run() {
            arr.getAndIncrement(0);
            arr1[0] = arr1[0]+1;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        arr1[0] = 0;
        Thread[] ts = new Thread[100];
        for (int k = 0; k < 100; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 100; k++) {
            ts[k].start();
        }

        for (int k = 0; k < 100; k++) {
            ts[k].join();
        }
        System.out.println(arr);
        System.out.println(arr1[0]);
    }
}
