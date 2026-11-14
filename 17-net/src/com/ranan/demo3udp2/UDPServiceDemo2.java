package com.ranan.demo3udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServiceDemo2 {
    public static void main(String[] args) throws Exception {
        // 实现UDP多收多发，服务端开发
        System.out.println("=====服务端启动=====");
        // 1.创建接收端对象，注册端口 8080
        DatagramSocket socket = new DatagramSocket(8080);

        // 2.创建一个数据包负责接收数据
        byte[] buf = new byte[1024 * 4];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        while (true) {
            // 3.接收数据
            socket.receive(packet); // 等待接收数据（可以多开客户端）

            // 4.看看数据是否收到了
            // 获取数据长度
            int len = packet.getLength();
            String data = new String(buf,0,len);
            System.out.println("服务端收到：" + data);

            // 获取对方的ip对象和程序端口
            InetAddress ip = packet.getAddress();
            int port = packet.getPort();
            System.out.println("发送端ip：" + ip.getHostAddress());
            System.out.println("发送端端口：" + port);

            System.out.println("=====================================");
        }
    }
}
