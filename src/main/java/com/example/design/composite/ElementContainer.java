package com.example.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-23 10:12
 **/
public class ElementContainer extends Component {
    private List<Component> subComponentList = new ArrayList<>();

    public ElementContainer(String name) {
        super(name);
    }

    public void addElement(Component c) {
        c.setParentName(this.getName());
        subComponentList.add(c);
    }

    public List<Component> getSubComponentList() {
        return subComponentList;
    }

    @Override
    public void print() {
        System.out.println("树枝节点: " + getName());
    }
}
