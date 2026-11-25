package com.ranan.demo7tcp4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TCPServiceDemo2 {
    public static void main(String[] args) throws Exception {
        // BS架构学习
        System.out.println("==========服务端启动==========");
        // 1.创建服务端ServerSocket对象，绑定端口号，监听客户端连接
        ServerSocket ss = new ServerSocket(8080);
        // 创建线程池
        ExecutorService pool = new ThreadPoolExecutor(3,10,10, TimeUnit.SECONDS
        ,new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

       while(true) {
           // 2.调用accept方法，阻塞等待客户端连接，一旦有客户端连接会返回一个Socket对象
           Socket socket = ss.accept();
           System.out.println("一个客户端上线了：" + socket.getInetAddress().getHostAddress());
           // 3.把这个客户端管道包装成一个任务交给线程池处理
           pool.execute(new ServerReader(socket)); // 线程可以当任务
       }
    }
}
