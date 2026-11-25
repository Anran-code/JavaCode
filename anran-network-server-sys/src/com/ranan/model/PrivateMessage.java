package com.ranan.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/** 一条私聊记录；客户端与服务端的字段和读写顺序必须保持一致。 */
public class PrivateMessage {
    private final long id; // 同一会话内递增，用来排序和去重
    private final long sentAt;
    private final String sender;
    private final String recipient;
    private final String content;

    public PrivateMessage(long id, long sentAt, String sender, String recipient, String content) {
        this.id = id;
        this.sentAt = sentAt;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    /** 文件和网络共用相同的数据顺序，正文中的换行不会破坏记录边界。 */
    public void writeTo(DataOutputStream out) throws IOException {
        out.writeLong(id);
        out.writeLong(sentAt);
        out.writeUTF(sender);
        out.writeUTF(recipient);
        out.writeUTF(content);
    }

    /** 按写入顺序读取一条完整记录。 */
    public static PrivateMessage readFrom(DataInputStream in) throws IOException {
        return new PrivateMessage(in.readLong(), in.readLong(), in.readUTF(), in.readUTF(), in.readUTF());
    }

    /** 原始数据单独保存，显示时才拼接昵称、时间和正文。 */
    public String getDisplayText() {
        String time = Instant.ofEpochMilli(sentAt).atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EEE a"));
        return sender + " " + time + "\n" + content;
    }

    public long getId() { return id; }
    public long getSentAt() { return sentAt; }
    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
}
