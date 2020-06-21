package com.example.design.singleton;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 18:06
 **/
public class Single03 {
    private static Single03 INSTANCE;

    private Single03() {

    }

    // 使用synchronize加锁
    public static synchronized Single03 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Single03();
        }
        return INSTANCE;
    }

}
