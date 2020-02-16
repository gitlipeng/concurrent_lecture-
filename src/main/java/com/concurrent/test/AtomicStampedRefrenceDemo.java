package com.concurrent.test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedRefrenceDemo {
    private static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19,0);

    public static void main(String[] args) {
        for(int i = 0;i < 3; i++){
            int stamp = money.getStamp();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int value = money.getReference();
//                    while (true){
                         if(money.compareAndSet(value,value+20,stamp,stamp + 1)){
                             System.out.println(Thread.currentThread().getName() + "充值20元成功");
                         }else {
                             System.out.println(Thread.currentThread().getName() + "修改失败");
                         }
//                    }

                }
            }).start();
        }

        for(int i = 0;i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int stamp = money.getStamp();
                    int value = money.getReference();
                    if(money.compareAndSet(value,value-10,stamp,stamp)){
                        System.out.println(Thread.currentThread().getName() + "消费成功" + ",余额：" + money.getReference());
                    }

                }
            }).start();
        }
    }
}
