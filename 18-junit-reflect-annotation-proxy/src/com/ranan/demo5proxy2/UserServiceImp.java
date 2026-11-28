package com.ranan.demo5proxy2;

public class UserServiceImp implements UserService {
    @Override
    public void login(String loginName, String password) throws Exception {
        // long start = System.currentTimeMillis();

        if ("admin".equals(loginName) && "123456".equals(password)) {
            System.out.println("您登录成功，欢迎光临本系统");
        } else {
            System.out.println("您登录失败，用户名或密码错误~");
        }
        Thread.sleep(1000);

//        long end = System.currentTimeMillis();
//        System.out.println("login方法耗时：" + (end - start) / 1000.0 + "秒");
    }

    @Override
    public void deleteUsers() throws Exception {
        // long start = System.currentTimeMillis();

        System.out.println("成功删除了1万个用户");
        Thread.sleep(1500);

//        long end = System.currentTimeMillis();
//        System.out.println("deleteUsers方法耗时：" + (end - start) / 1000.0 + "秒");
    }

    @Override
    public String[] selectUsers() throws Exception {
        // long start = System.currentTimeMillis();

        System.out.println("查询出了3个用户");
        String[] names = {"张金伍", "李三狗", "牛爱花"};
        Thread.sleep(500);

//        long end = System.currentTimeMillis();
//        System.out.println("selectUsers方法耗时：" + (end - start) / 1000.0 + "秒");
        return names;
    }
}
