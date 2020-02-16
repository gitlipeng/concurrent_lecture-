package com.concurrent.test;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefrenceTest {
    private static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(atomicReference.compareAndSet("abc","def")){
                        System.out.println(atomicReference.get());
                        System.out.println(Thread.currentThread().getName() + "修改成功");
                    }else{
                        System.out.println(Thread.currentThread().getName() + "修改失败");
                    }

                }
            }).start();
        }

    }
}
