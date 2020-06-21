package com.example.design.singleton;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 17:34
 **/
public class Client {
    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(() ->{
                System.out.println(Single02.getInstance().hashCode());
            }).start();
        }
    }
}
