package com.example.design.singleton.ex;

import com.example.design.singleton.Single01;
import com.example.design.singleton.Single06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-21 18:54
 **/
public class SerializeTest {

    private static void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
//            Single01 single = Single01.getInstance();
            Single06 single = Single06.INSTANCE;
            oos.writeObject(single);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
//            Single01 single = (Single01) ois.readObject();
            Single06 single = (Single06) ois.readObject();
            System.out.println(single);

//            System.out.println(single == Single01.getInstance()); // return false
            System.out.println(single == Single06.INSTANCE); //return true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write();
        read();
    }

}
