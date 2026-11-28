package com.ranan.demo4proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
* 代理工具类：中介公司，专门负责创建代理对象并返回给别人使用
* */
public class ProxyUtil {
    // 创建一个明星对象的代理对象返回
    // public static Object newProxyInstance(
    // ClassLoader loader,           // 第一个参数：指定用哪个类加载器，去加载生成的代理类
    // Class<?>[] interface,         // 第二个参数：指定接口，这些接口用于指定生成的代理要实现什么接口，同类的接口实现
    // InvocationHandler h);         // 第三个参数：用来指定生成的代理对象要干什么事情
    public static StarService createProxy(Star s) {
        StarService proxy = (StarService) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                s.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 用来声明代理对象要干的事情
                        // 参数一：proxy接收到的代理对象本身
                        // 参数二：method表示被代理此时调用的方法（sing、dance）
                        // 参数三：args表示被代理的方法的参数
                        String methodName = method.getName();
                        if("sing".equals(methodName)) {
                            System.out.println("准备话筒，20万元！");
                        } else if ("dance".equals(methodName)) {
                            System.out.println("准备场地，30万元！");
                        }
                        // 真正干活（把真正的明星请过来干活）
                        // 真正的method方法：
                        return method.invoke(s,args);
                    }
                }
        );
        return proxy;
    }
}
