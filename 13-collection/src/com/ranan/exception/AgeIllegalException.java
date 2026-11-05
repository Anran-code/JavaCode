package com.ranan.exception;

/*
* 自定义编译时异常
* 1.继承Exception
* 2.定义构造方法，并调用父类构造方法
* */
public class AgeIllegalException extends Exception{
    public AgeIllegalException(){}
    public AgeIllegalException(String massage){
        super(massage);
    }
}
