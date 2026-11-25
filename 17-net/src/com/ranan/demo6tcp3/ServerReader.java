package com.ranan.demo6tcp3;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ServerReader extends Thread{
    private Socket socket;

    public ServerReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        // 读取管道的消息
        try {
            // 获取输入流，读取数据
            InputStream is = socket.getInputStream();
            // 包装成特殊数据输入流
            DataInputStream dis = new DataInputStream(is);
            while (true) {
                String msg = dis.readUTF();
                System.out.println(msg);
                System.out.println("客户端的ip: " + socket.getInetAddress().getHostAddress());
                System.out.println("客户端的端口: " + socket.getPort());
                System.out.println("====================================");
            }
        } catch (Exception e) {
            System.out.println("客户端：" + socket.getInetAddress().getHostAddress() + "下线了");
        }
    }
}
