package com.ranan.demo3annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD}) // 表示注解的目标为方法/成员变量
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest1 {
}
