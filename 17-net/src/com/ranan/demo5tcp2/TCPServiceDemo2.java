package com.ranan.demo5tcp2;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServiceDemo2 {
    public static void main(String[] args) throws Exception {
        // 实现TCP通信下的多发多收，服务端开发
        System.out.println("==========服务端启动==========");
        // 1.创建服务端ServerSocket对象，绑定端口号，监听客户端连接
        ServerSocket ss = new ServerSocket(9999);
        // 2.调用accept方法，阻塞等待客户端连接，一旦有客户端连接会返回一个Socket对象
        Socket socket = ss.accept();
        // 3.获取输入流，读取数据
        InputStream is = socket.getInputStream();
        // 4.包装成特殊数据输入流
        DataInputStream dis = new DataInputStream(is);
        // 5.获取数据
        while (true) {
            String msg = dis.readUTF();
            System.out.println(msg);
            System.out.println("客户端的ip: " + socket.getInetAddress().getHostAddress());
            System.out.println("客户端的端口: " + socket.getPort());
            System.out.println("====================================");
        }
    }
}
