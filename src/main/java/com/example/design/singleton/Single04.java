package com.example.design.singleton;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 18:12
 **/
public class Single04 {
    private static Single04 INSTANCE;

    private Single04() {

    }

    public static Single04 getInstance() {
        if (INSTANCE == null) {
            // 把 synchronize 放到方法里面, 只在部分代码上加锁
            synchronized (Single04.class) {
                // 双重判断, 否则还是没有把null判断和new两个动作作为一个整体
                if (INSTANCE == null) {
                    INSTANCE = new Single04();
                }
            }

        }
        return INSTANCE;
    }
}
