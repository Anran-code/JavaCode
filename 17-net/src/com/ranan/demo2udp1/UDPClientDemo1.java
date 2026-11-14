package com.ranan.demo2udp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientDemo1 {
    public static void main(String[] args) throws Exception {
        // 实现UDP一收一发，客户端开发
        System.out.println("=====客户端启动=====");
        // 1.创建发送端对象
        DatagramSocket socket = new DatagramSocket(7777);

        // 2.创建数据包对象封装要发送的消息
        byte[] bytes = "Hello World!".getBytes();
        // 1) byte[] buf 要发送的数据
        // 2) int length 发送的字节长度
        // 3) InetAddress address 目的地IP地址
        // 4) int port 服务端程序的端口号
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),8080);

        // 3.让发送端对象发送数据包的数据
        socket.send(packet);

        socket.close();
    }
}
