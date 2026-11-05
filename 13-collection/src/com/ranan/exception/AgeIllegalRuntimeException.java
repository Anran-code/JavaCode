package com.ranan.exception;

/*
* 自定义运行时异常
* 1.继承RuntimeException
* 2.定义构造方法，并调用父类构造方法
* */
public class AgeIllegalRuntimeException extends RuntimeException{
    public AgeIllegalRuntimeException(){}
    public AgeIllegalRuntimeException(String massage){
        super(massage);
    }
}
