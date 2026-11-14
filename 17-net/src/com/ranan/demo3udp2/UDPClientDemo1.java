package com.ranan.demo3udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientDemo1 {
    public static void main(String[] args) throws Exception {
        // 实现UDP多收多发，客户端开发
        System.out.println("=====客户端启动=====");
        // 1.创建发送端对象
        DatagramSocket socket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            // 2.创建数据包对象封装要发送的消息
            System.out.print("请输入：");
            String msg = sc.nextLine();

            // exit 退出
            if(msg.equals("exit")) {
                System.out.println("=====客户端退出=====");
                socket.close();
                break;
            }

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length,
                    InetAddress.getLocalHost(),8080);

            // 3.让发送端对象发送数据包的数据
            socket.send(packet);
        }
    }
}
