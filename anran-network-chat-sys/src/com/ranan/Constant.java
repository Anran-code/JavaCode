package com.ranan;

/** 客户端连接配置和消息编号，需与服务端保持一致。 */
public class Constant {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 6666;
    public static final int LOGIN_MESSAGE = 1;
    public static final int LOGIN_LIST_MESSAGE = 1; // 服务端返回：人数 + 各用户昵称
    public static final int GROUP_CHAT_MESSAGE = 2;
    // 发给服务端：UTF 正文、接收人；返回：PrivateMessage（ID、时间、双方昵称、正文）。
    public static final int PRIVATE_CHAT_MESSAGE = 3;
    public static final int PRIVATE_HISTORY_REQUEST = 5; // 请求：对方昵称、请求编号（UTF）
    public static final int PRIVATE_HISTORY_RESPONSE = 6; // 返回：对方、请求编号、条数、记录
    public static final int PRIVATE_HISTORY_ERROR = 7; // 返回：对方、请求编号、错误原因
    public static final int PRIVATE_CHAT_ERROR = 4; // 服务端返回：接收者昵称 + 失败原因
}
