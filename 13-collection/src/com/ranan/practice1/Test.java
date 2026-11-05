package com.ranan.practice1;

public class Test {
    public static void main(String[] args) {
        // 完成电影案例
        // 1.创建电影对象，定义电影类
        // 2.创建电影操作对象，负责业务逻辑
        MovieService movieService = new MovieService();
        movieService.start();
    }
}
