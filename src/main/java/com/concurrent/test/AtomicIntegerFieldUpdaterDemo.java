package com.concurrent.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score=0; // 修改点1，将变量前面加上volatile
    }

    // 修改点2，定义一个AtomicIntegerFieldUpdater
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];

        for (int k = 0; k < 10000; k++) {
            t[k] = new Thread(){
                @Override
                public void run() {
                    // 修改点3，使用这种方式更新
                    for(int i =0;i < 1000;i++){
//                        stu.score++;
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[k].start();
        }

        for (int k = 0; k < 10000; k++) {
            t[k].join();
        }

        System.out.println("score=" + stu.score);
        System.out.println("allScore=" + allScore);
    }

}
