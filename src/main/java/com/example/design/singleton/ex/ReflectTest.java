package com.example.design.singleton.ex;

import com.example.design.singleton.Single01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 19:19
 **/
public class ReflectTest {
    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        InvocationTargetException {
        Single01 s1 = Single01.getInstance();
        final Class<?> singleClass = Class.forName("com.example.design.singleton.Single01");
        Constructor<?> declaredConstructor = singleClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        final Single01 s11 = (Single01)declaredConstructor.newInstance();

        System.out.println(s1 == s11);
    }
}
