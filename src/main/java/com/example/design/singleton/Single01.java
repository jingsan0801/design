package com.example.design.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 17:33
 **/
public class Single01 implements Serializable {
    // static 因为要在 static 的 getInstance()中使用
    // final 是为了避免被修改
    private static final Single01 INSTANCE = new Single01();

    private Single01(){

    }

    public static Single01 getInstance() {
        return INSTANCE;
    }


    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }
}
