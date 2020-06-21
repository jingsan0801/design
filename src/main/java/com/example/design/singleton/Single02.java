package com.example.design.singleton;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 17:49
 **/
public class Single02 {
    private static Single02 INSTANCE;

    private Single02() {

    }

    public static synchronized Single02 getInstance(){
        if(INSTANCE == null){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Single02();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(() ->{
                System.out.println(Single02.getInstance());
            }).start();
        }
    }
}
