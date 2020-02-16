package com.concurrent.test;

public class Test1 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(10);
        Thread thread1 = new Thread(new MyThread(person));
        thread1.setName("线程1");
        thread1.start();

        Thread thread2 = new Thread(new MyThread(person));
        thread2.setName("线程2");
        thread2.start();

        for (int i = 0; i < 100; i++){
            System.out.println("主线程获取年龄："+ person.getAge());
            try {
                Thread.sleep(500l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread implements Runnable{
    Person person;

    public MyThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            if("线程1".equals(Thread.currentThread().getName())){
                person.setAge(person.getAge() + 1);
            }
            System.out.println(Thread.currentThread().getName() + "线程获取年龄："+ person.getAge());
            try {
                Thread.sleep(500l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}