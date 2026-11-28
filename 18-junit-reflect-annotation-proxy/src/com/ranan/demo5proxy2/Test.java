package com.ranan.demo5proxy2;

import java.util.Arrays;

/**
 * 先直接调用用户业务方法，观察每个方法内部都写了耗时统计。
 * 若用动态代理来实现，就不需要对每个方法单独进行好事统计
 */
public class Test {
    public static void main(String[] args) throws Exception {
        // 1. 创建用户业务对象。
        // UserService userService = new UserServiceImp();
        UserService userService = ProxyUtil.createProxy(new UserServiceImp());

        // 2. 调用用户业务功能。
        userService.login("admin", "123456");
        userService.deleteUsers();

        String[] names = userService.selectUsers();
        System.out.println("查询到的用户是：" + Arrays.toString(names));
    }
}
