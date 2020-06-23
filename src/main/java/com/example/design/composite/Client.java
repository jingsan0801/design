package com.example.design.composite;

/**
 * @description:
 * @author: jcwang
 * @create: 2020-06-23 09:40
 **/
public class Client {
    private static ElementContainer root;

    public static void main(String[] args) {
        init();
        root.print();
        printInfo(root);

/*
        树枝节点: window窗口
        window窗口 的叶子节点: logo图片
        树枝节点: 登录Frame
        登录Frame 的叶子节点: 用户名label
        登录Frame 的叶子节点: 用户名文本框
        登录Frame 的叶子节点: 密码label
        登录Frame 的叶子节点: 密码文本框
        登录Frame 的叶子节点: 是否记住密码复选框
        登录Frame 的叶子节点: 记住密码label
        登录Frame 的叶子节点: 忘记密码link
        window窗口 的叶子节点: 登录按钮
        window窗口 的叶子节点: 注册按钮

        */
    }

    private static void init() {
        // 创建对象
        ElementContainer window = new ElementContainer("window窗口");
        ElementContainer frame = new ElementContainer("登录Frame");

        Component logoPic = new ElementComponent("logo图片");
        Component loginButton = new ElementComponent("登录按钮");
        Component registerButton = new ElementComponent("注册按钮");

        Component usernameLabel = new ElementComponent("用户名label");
        Component usernameTextBox = new ElementComponent("用户名文本框");
        Component passwordLabel = new ElementComponent("密码label");
        Component passwordTextBox = new ElementComponent("密码文本框");
        Component rememberPasswordCheckBox = new ElementComponent("是否记住密码复选框");
        Component rememberPasswordLabel = new ElementComponent("记住密码label");
        Component forgetPasswordLink = new ElementComponent("忘记密码link");

        // 组装
        window.addElement(logoPic);
        window.addElement(frame);
        window.addElement(loginButton);
        window.addElement(registerButton);

        frame.addElement(usernameLabel);
        frame.addElement(usernameTextBox);
        frame.addElement(passwordLabel);
        frame.addElement(passwordTextBox);
        frame.addElement(rememberPasswordCheckBox);
        frame.addElement(rememberPasswordLabel);
        frame.addElement(forgetPasswordLink);

        root = window;
    }

    private static void printInfo(ElementContainer root) {
        if (root != null && !root.getSubComponentList().isEmpty()) {
            for (Component e : root.getSubComponentList()) {
                e.print();
                if (e instanceof ElementContainer) {
                    printInfo((ElementContainer)e);
                }
            }
        }

    }
}
