package com.ranan;

import com.ranan.gui.ClientChatFrame;
import com.ranan.model.PrivateMessage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/** 持续接收服务端消息，同一个 Socket 只交给这一个线程读取。 */
public class ClientReaderThread extends Thread {
    private final Socket socket;
    private final ClientChatFrame chatFrame;

    /** 保存登录时建立的连接和需要更新的聊天窗口。 */
    public ClientReaderThread(Socket socket, ClientChatFrame chatFrame) {
        super("client-reader");
        this.socket = socket;
        this.chatFrame = chatFrame;
    }

    /** 按“消息类型 + 对应内容”读取，等待消息时不会阻塞界面。 */
    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            while (!socket.isClosed()) {
                int type = dis.readInt();
                switch (type) {
                    case Constant.LOGIN_LIST_MESSAGE:
                        int count = dis.readInt();
                        if (count < 0) {
                            throw new IOException("在线人数不能为负数");
                        }
                        ArrayList<String> names = new ArrayList<>();
                        for (int i = 0; i < count; i++) {
                            names.add(dis.readUTF());
                        }
                        // 完整读取这一份名单后，再交给界面一次性更新。
                        chatFrame.updateOnlineUsers(names);
                        break;
                    case Constant.GROUP_CHAT_MESSAGE:
                        // 服务端先 writeInt(群聊编号)，再 writeUTF(拼好的昵称、时间和正文)。
                        // 上面的 readInt() 已经读完编号，这里只需要读取消息内容。
                        String message = dis.readUTF();
                        chatFrame.appendMessage(message);
                        break;
                    case Constant.PRIVATE_CHAT_MESSAGE:
                        // 与文件记录相同：ID、时间、发送人、接收人、正文。
                        chatFrame.receivePrivateMessage(PrivateMessage.readFrom(dis));
                        break;
                    case Constant.PRIVATE_HISTORY_RESPONSE:
                        String peer = dis.readUTF();
                        String requestId = dis.readUTF();
                        int historyCount = dis.readInt();
                        if (historyCount < 0 || historyCount > 50) {
                            throw new IOException("历史记录条数不正确");
                        }
                        ArrayList<PrivateMessage> history = new ArrayList<>();
                        for (int i = 0; i < historyCount; i++) {
                            history.add(PrivateMessage.readFrom(dis));
                        }
                        chatFrame.receivePrivateHistory(peer, requestId, history);
                        break;
                    case Constant.PRIVATE_HISTORY_ERROR:
                        chatFrame.receivePrivateHistoryError(dis.readUTF(), dis.readUTF(), dis.readUTF());
                        break;
                    case Constant.PRIVATE_CHAT_ERROR:
                        chatFrame.showPrivateError(dis.readUTF(), dis.readUTF());
                        break;
                    default:
                        throw new IOException("未知消息类型：" + type);
                }
            }
        } catch (IOException e) {
            chatFrame.showDisconnected();
        }
    }
}
