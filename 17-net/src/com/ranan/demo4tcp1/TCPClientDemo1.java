package com.ranan.demo4tcp1;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClientDemo1 {
    public static void main(String[] args) throws Exception{
        // 实现TCP通信下的一发一收，客户端开发
        // 1.Socket管道对象，请求与服务端的Socket链接，可靠链接
        Socket s = new Socket("127.0.0.1",9999);

        // 2.从Socket信号管道中得到一个字节输出流
        OutputStream os = s.getOutputStream();

        // 3.特殊数据流
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(1);
        dos.writeUTF("你好");

        s.close();
    }
}
