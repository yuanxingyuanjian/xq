/**
 * Copyright (C), 2011-2019, 超清股份有限公司
 * FileName: ThreadDemo
 * Author:   Administrator
 * Date:     2019/11/14/014 10:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.it.demo;

public class ThreadDemo {
    public static void mian(String args[]){
        Reaource res = new Reaource();
        new Thread(new Producer(res)).start();
        new Thread(new Consumer(res)).start();
    }

}

class Computer {
    private static int count = 0 ;
    private String name ;
    private double price ;
    public Computer(String name, double price){
        this.name = name ;
        this.price = price ;
    }
    public String toString(){
        return "第" + count + "台电脑" + "电脑名字：" + this.name + "电脑价格：" + this.price;
    }
}

class Reaource{
    private Computer computer;
    private boolean flag = true;

    public synchronized void make() throws Exception{
        if(this.computer != null){
            super.wait();
        }
        Thread.sleep(100);
        this.computer = new Computer("MLDN电脑",1.1);
        super.notify();
    }

    public synchronized void get() throws Exception{
        if(this.computer == null){
            super.wait();
        }
        Thread.sleep(10);
        System.out.println(this.computer.toString());
        this.computer = null ;
        super.notify();
    }

}

class Producer implements Runnable{
    private Reaource reaource;
    public Producer(Reaource reaource){
        this.reaource = reaource;
    }

    public void run() {
        try {
            reaource.make();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    private Reaource reaource;
    public Consumer(Reaource reaource){
        this.reaource = reaource;
    }

    public void run() {
        try {
            reaource.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}