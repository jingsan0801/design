package com.example.design.singleton;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 18:24
 **/
public class Single05 {

    private Single05() {

    }

    // 静态内部类在外部类被加载时, 是不会被加载的, 而是在被调用的时候才会被加载
    private static class Single05Holder{
        private final static Single05 INSTANCE = new Single05();
    }

    public static Single05 getInstance() {
        return Single05Holder.INSTANCE;
    }
}
