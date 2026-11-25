package com.ranan;

public class Constant {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 6666;

    public static final int OTHER_MESSAGE = 0; // 其他消息

    // 客户端发给服务端的消息类型
    public static final int LOGIN_MESSAGE = 1; // 登录：后面跟昵称
    public static final int GROUP_CHAT_MESSAGE = 2; // 群聊消息
    // 接收：UTF 正文、接收人；转发：PrivateMessage（ID、时间、双方昵称、正文）。
    public static final int PRIVATE_CHAT_MESSAGE = 3;
    public static final int PRIVATE_HISTORY_REQUEST = 5; // 请求：对方昵称、请求编号（UTF）
    public static final int PRIVATE_HISTORY_RESPONSE = 6; // 返回：对方、请求编号、条数、记录
    public static final int PRIVATE_HISTORY_ERROR = 7; // 返回：对方、请求编号、错误原因

    // 服务端发给客户端的消息类型，与上面的编号按通信方向分别解释
    public static final int LOGIN_LIST_MESSAGE = 1; // 在线名单：后面跟人数、各用户昵称
    public static final int PRIVATE_CHAT_ERROR = 4; // 私聊失败：接收者昵称 + 失败原因
}
