package com.ranan;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Server {
    // 定义集合容器存储所有登陆进来的客户端管道
    // 用map集合实现socket到人的映射
    // 多个客户端线程会同时登录、退出和查询，需要使用线程安全的集合。
    public static final Map<Socket,String> onlineSocket = new ConcurrentHashMap<>();
    // 所有连接共享同一个存储对象，synchronized 才能保护同一份文件。
    public static final PrivateHistoryStore privateHistory = new PrivateHistoryStore(PrivateHistoryStore.defaultDirectory());

    public static void main(String[] args) {
        System.out.println("启动服务端系统...");
        // 先成功占用端口，再清理历史，避免误启动第二个服务端时清掉正在使用的数据。
        try (ServerSocket serverSocket = new ServerSocket(Constant.SERVER_PORT)) {
            privateHistory.startSession();
            // JVM 正常退出时执行；任务管理器强杀等情况可能不执行，由下次启动兜底清理。
            Runtime.getRuntime().addShutdownHook(new Thread(Server::clearHistoryOnShutdown, "history-cleanup"));
            try {
                // 主线程接收客户端连接请求。
                while (true) {
                    System.out.println("等待客户端连接...");
                    Socket socket = serverSocket.accept();
                    new ServerReaderThread(socket).start();
                    System.out.println("一个客户端连接成功...");
                }
            } finally {
                // 接收循环异常结束时也清理，而不只依赖 JVM 退出。
                clearHistoryOnShutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 服务端停止时清空本次运行的所有私聊历史，重复调用也安全。 */
    private static void clearHistoryOnShutdown() {
        try {
            privateHistory.closeAndClear();
        } catch (IOException e) {
            System.err.println("清理私聊历史失败，下次启动将再次尝试清理。");
            e.printStackTrace();
        }
    }
}

