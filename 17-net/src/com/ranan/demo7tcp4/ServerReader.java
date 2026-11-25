package com.ranan.demo7tcp4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerReader extends Thread{
    private Socket socket;

    public ServerReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        // 读取管道的消息
        try(
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                // 给浏览器响应数据
                OutputStream os = socket.getOutputStream();
                // 通过字节输出流包装写出去的数据给浏览器
                PrintStream ps = new PrintStream(os, false, StandardCharsets.UTF_8);
                ) {
            // 浏览器 GET 请求的请求头以空行结束，先读完再发送响应
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                // 当前练习只展示固定页面，暂不处理请求头内容
            }
            // 写响应的网页数据
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type: text/html; charset=utf-8"); // 声明响应正文是 HTML
            ps.println("Connection: close"); // 本次响应结束后关闭连接
            ps.println(); // 必须换一行
            ps.println("<html>");
            ps.println("<head>");
            ps.println("<meta charset='utf-8'>");
            ps.println("<title>我的网页</title>"); // 浏览器标签页标题
            ps.println("</head>");
            ps.println("<body>"); // 页面上显示的内容放在 body 中
            ps.println("<h1 style='color:red;font-size:20px'>Hello world!</h1>");
            ps.println("</body>");
            ps.println("</html>");
            ps.flush(); // 先发送完整响应，再由 try-with-resources 关闭流及关联的 Socket
        } catch (Exception e) {
            System.out.println("客户端：" + socket.getInetAddress().getHostAddress() + "下线了");
        }
    }
}
