package com.ranan;

import com.ranan.model.PrivateMessage;
import java.io.DataInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.util.List;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Map;
import java.util.Objects;

public class ServerReaderThread extends Thread{
    private Socket socket;

    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 接受的消息可能有多种类型：
            // 1.登陆消息 2.群聊消息 3.私聊消息
            // 所以客户端必须声明协议发送消息
            // 比如先发1代表登录 先发2代表群聊消息
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) { // 确保能收到多个信息
                int type = dis.readInt();
                switch (type) {
                    case Constant.LOGIN_MESSAGE:
                        // 客户端发来了登陆消息，接下来要接收昵称数据，再更新全部在线客户端的在线人数列表
                        String nickname = dis.readUTF();
                        // 把这个登陆成功的客户端socket加入集合
                        Server.onlineSocket.put(socket,nickname);
                        // 更新客户端的在线人数列表
                        updateClientOnlineUserList();
                        break;
                    case Constant.GROUP_CHAT_MESSAGE:
                        // 客户端发来了群聊消息，接下来要接收群聊消息内容，再把群聊消息转发给全部在线客户端
                        String msg1 = dis.readUTF();
                        sendMsgtoAll(msg1);
                        break;
                    case Constant.PRIVATE_CHAT_MESSAGE:
                        // 客户端发来了私聊消息，接下来要接收私聊消息内容，再把私聊消息转发给指定客户端
                        String msg2 = dis.readUTF();
                        String name = dis.readUTF();
                        sendMsgtoSomeone(msg2,name);
                        break;
                    case Constant.PRIVATE_HISTORY_REQUEST:
                        sendPrivateHistory(dis.readUTF(), dis.readUTF());
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("客户端：" + Server.onlineSocket.get(socket) +
                    "(" + socket.getInetAddress().getHostAddress() + ")下线了");
            Server.onlineSocket.remove(socket); // 下线用户从map里面移除
            try {
                socket.close();
            } catch (IOException closeError) {
                closeError.printStackTrace();
            }
            updateClientOnlineUserList(); // 更新列表
        }
    }

    /** 取一份名单快照，使人数和昵称数量一致，再完整发送给每个在线连接。 */
    private void updateClientOnlineUserList() {
        synchronized (Server.onlineSocket) {
            List<String> names = new ArrayList<>(Server.onlineSocket.values());
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(buffer);
                dos.writeInt(Constant.LOGIN_LIST_MESSAGE);
                dos.writeInt(names.size());
                for (String name : names) {
                    dos.writeUTF(name);
                }
                for (Socket target : Server.onlineSocket.keySet()) {
                    try {
                        writePacket(target, buffer.toByteArray());
                    } catch (IOException e) {
                        // 一个连接发送失败，不应影响其他人的名单更新。
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 拼好发送人、时间和正文，时间只出现在第一行。 */
    private String formatMessage(String sender, String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EEE a"));
        return sender + " " + time + "\r\n" + message;
    }

    /** 群聊发给所有人，包括发送者自己。 */
    private void sendMsgtoAll(String message) {
        String text = formatMessage(Server.onlineSocket.get(socket), message);
        for (Socket target : Server.onlineSocket.keySet()) {
            try {
                sendTextPacket(target, Constant.GROUP_CHAT_MESSAGE, text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 私聊只发给指定对象，并回传一份给发送者，供双方各自的私聊窗口显示。 */
    private void sendMsgtoSomeone(String message, String recipient) throws IOException {
        String sender = Server.onlineSocket.get(socket);
        Socket receiver = null;
        for (Map.Entry<Socket, String> entry : Server.onlineSocket.entrySet()) {
            if (Objects.equals(entry.getValue(), recipient)) {
                if (receiver != null) {
                    sendTextPacket(socket, Constant.PRIVATE_CHAT_ERROR, recipient, "存在同名用户，无法确定私聊对象，请使用不同昵称。");
                    return;
                }
                receiver = entry.getKey();
            }
        }
        if (receiver == null) {
            sendTextPacket(socket, Constant.PRIVATE_CHAT_ERROR, recipient, "对方已离线，消息未发送。");
            return;
        }
        PrivateMessage saved;
        try {
            // 先保存成功再转发，双方收到的实时消息和历史记录使用同一个 ID。
            saved = Server.privateHistory.save(sender, recipient, message);
        } catch (UTFDataFormatException e) {
            sendTextPacket(socket, Constant.PRIVATE_CHAT_ERROR, recipient, "消息太长，消息未发送。");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            sendTextPacket(socket, Constant.PRIVATE_CHAT_ERROR, recipient, "保存历史记录失败，消息未发送。");
            return;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buffer);
        out.writeInt(Constant.PRIVATE_CHAT_MESSAGE);
        saved.writeTo(out);
        byte[] packet = buffer.toByteArray();
        try {
            writePacket(receiver, packet);
        } catch (IOException e) {
            sendTextPacket(socket, Constant.PRIVATE_CHAT_ERROR, recipient, "记录已保存，但对方连接已断开，可稍后从历史记录查看。");
        }
        if (receiver != socket) {
            writePacket(socket, packet);
        }
    }

    /** 查询当前登录用户与指定对象的最近 50 条，允许对方离线时查看。 */
    private void sendPrivateHistory(String peer, String requestId) throws IOException {
        String currentUser = Server.onlineSocket.get(socket);
        if (currentUser == null) {
            throw new IOException("请先登录再查询历史记录");
        }
        List<PrivateMessage> messages;
        try {
            messages = Server.privateHistory.load(currentUser, peer);
        } catch (IOException e) {
            e.printStackTrace();
            sendTextPacket(socket, Constant.PRIVATE_HISTORY_ERROR, peer, requestId, "历史记录读取失败，请关闭窗口后重试。");
            return;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buffer);
        out.writeInt(Constant.PRIVATE_HISTORY_RESPONSE);
        out.writeUTF(peer);
        out.writeUTF(requestId);
        out.writeInt(messages.size());
        for (PrivateMessage message : messages) {
            message.writeTo(out);
        }
        writePacket(socket, buffer.toByteArray());
    }

    /** 先在内存中编码完整消息，再写入连接，避免编码失败后留下半条消息。 */
    private void sendTextPacket(Socket target, int type, String... fields) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        dos.writeInt(type);
        for (String field : fields) {
            dos.writeUTF(field);
        }
        writePacket(target, buffer.toByteArray());
    }

    /** 群聊、私聊、名单可能由不同线程写到同一连接，锁住完整消息防止字节交错。 */
    private void writePacket(Socket target, byte[] packet) throws IOException {
        synchronized (target) {
            target.getOutputStream().write(packet);
            target.getOutputStream().flush();
        }
    }
}
