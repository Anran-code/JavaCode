package com.ranan.exception;

public class ExceptionDemo3 {
    public static void main(String[] args) {
        // 学习自定义异常
        try {
            saveAge(300);
        } catch (AgeIllegalException e) {
            e.printStackTrace();
            System.out.println("保存年龄失败了...");
        }

        saveAge2(300);
    }


    // 需求：我们公司只要收到了年龄小于1或大于200的年龄就是一个非法异常
    // 编译类异常（必须要处理：在本方法里面catch，或者throws交给调用者处理）
    // 继承自Exception
    public static void saveAge(int age) throws AgeIllegalException {
        if(age < 1 || age > 200){
            throw new AgeIllegalException("年龄非法");
        } else {
            System.out.println("年龄合法");
        }

        // 或者
//        try{
//            if(age < 1 || age > 200){
//                throw new AgeIllegalException("年龄非法");
//            }
//        } catch (AgeIllegalException e) {
//            throw new RuntimeException(e);
//        }
    }

    // 运行时异常：可以不处理，继承自RuntimeException
    public static void saveAge2(int age){
        if(age < 1 || age > 200){
            throw new AgeIllegalRuntimeException("年龄非法");
        } else {
            System.out.println("年龄合法");
        }
    }
}
