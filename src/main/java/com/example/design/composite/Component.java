package com.example.design.composite;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-23 10:15
 **/
public abstract class Component {
    private final String name;
    private String parentName;

    public Component(String name) {
       this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public abstract void print();

}
