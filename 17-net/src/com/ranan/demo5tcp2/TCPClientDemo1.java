package com.ranan.demo5tcp2;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientDemo1 {
    public static void main(String[] args) throws Exception{
        // 实现TCP通信下的多发多收，客户端开发
        System.out.println("==========客户端启动==========");
        // 1.Socket管道对象，请求与服务端的Socket链接，可靠链接
        Socket s = new Socket("127.0.0.1",9999);

        // 2.从Socket信号管道中得到一个字节输出流
        OutputStream os = s.getOutputStream();

        // 3.特殊数据流
        DataOutputStream dos = new DataOutputStream(os);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入：");
            String msg = sc.nextLine();
            if(msg.equals("exit")) {
                System.out.print("退出成功");
                dos.close();
                break;
            }
            dos.writeUTF(msg);
        }
    }
}
