package com.ranan.demo5proxy2;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static <T> T createProxy(T obj) { // 使用泛型方法
        T proxy = (T) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy1, method, args) -> {
                    long start = System.currentTimeMillis();
                    Object answer = method.invoke(obj,args);
                    long end = System.currentTimeMillis();
                    System.out.println(method.getName() + "方法耗时：" + (end - start) / 1000.0 + "秒");
                    return answer;
                });
        return proxy;
    }

//    public static UserService createProxy(UserServiceImp usi) {
//        UserService proxy = (UserService) Proxy.newProxyInstance(
//                ProxyUtil.class.getClassLoader(),
//                usi.getClass().getInterfaces(),
//                (proxy1, method, args) -> {
//                    long start = System.currentTimeMillis();
//                    Object answer = method.invoke(usi,args);
//                    long end = System.currentTimeMillis();
//                    System.out.println(method.getName() + "方法耗时：" + (end - start) / 1000.0 + "秒");
//                    return answer;
//                });
//        return proxy;
//    }
}
