package com.ranan.demo4proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        // 创建代理对象
        // 1.准备一个明星对象，设计明星类
        Star star = new Star("Anran");
        // 2.为明星创建一个专门的动态代理
        StarService proxy = ProxyUtil.createProxy(star);
        proxy.sing("Happy Birthday to You");
        System.out.println(proxy.dance());
    }
}
