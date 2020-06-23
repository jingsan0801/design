package com.example.design.composite;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-23 10:23
 **/
public class ElementComponent extends Component{

    public ElementComponent(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(getParentName() + " 的叶子节点: " + getName());
    }
}
