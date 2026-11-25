package com.ranan;

import com.ranan.model.PrivateMessage;
import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

/** 本次服务端运行期间的文件存储：每个私聊保留最近 50 条，关闭时清空。 */
public class PrivateHistoryStore {
    public static final int MAX_MESSAGES = 50;
    private static final int FILE_MAGIC = 0x43484154;
    private static final int FILE_VERSION = 1;
    private final Path directory;
    private boolean closed;

    public PrivateHistoryStore(Path directory) {
        this.directory = directory.toAbsolutePath().normalize();
    }

    /** 启动新会话前清除上次异常退出留下的记录；必须在接收客户端之前调用。 */
    public synchronized void startSession() throws IOException {
        clearHistoryFiles();
        closed = false;
    }

    /** 先禁止继续读写，再清空文件，避免客户端线程在清理之后重新写出历史。 */
    public synchronized void closeAndClear() throws IOException {
        closed = true;
        clearHistoryFiles();
    }

    /** 只删除本存储生成的历史文件和临时文件，不递归删除目录或其他文件。 */
    private void clearHistoryFiles() throws IOException {
        if (Files.notExists(directory)) {
            return;
        }
        IOException failure = null;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path file : files) {
                String name = file.getFileName().toString();
                boolean historyFile = name.matches("[0-9a-f]{64}\\.dat");
                boolean temporaryFile = name.matches("history-.*\\.tmp");
                if ((historyFile || temporaryFile) && file.toAbsolutePath().normalize().getParent().equals(directory)
                        && Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS)) {
                    try {
                        Files.deleteIfExists(file);
                    } catch (IOException e) {
                        if (failure == null) {
                            failure = new IOException("部分私聊历史文件清理失败");
                        }
                        failure.addSuppressed(e);
                    }
                }
            }
        }
        if (failure != null) {
            throw failure;
        }
    }

    /** 默认写到服务端模块的 chat-data/private；测试时可用系统属性单独指定目录。 */
    public static Path defaultDirectory() {
        String configured = System.getProperty("chat.history.dir");
        if (configured != null) {
            return Path.of(configured);
        }
        Path working = Path.of("").toAbsolutePath();
        Path module = working.endsWith("anran-network-server-sys")
                ? working : working.resolve("anran-network-server-sys");
        return module.resolve("chat-data/private");
    }

    /** 读旧记录、分配递增 ID、裁剪到 50 条，再安全替换文件；失败时不转发消息。 */
    public synchronized PrivateMessage save(String sender, String recipient, String content) throws IOException {
        List<PrivateMessage> messages = load(sender, recipient);
        long id = messages.isEmpty() ? 1 : messages.get(messages.size() - 1).getId() + 1;
        PrivateMessage message = new PrivateMessage(id, System.currentTimeMillis(), sender, recipient, content);
        messages.add(message);
        if (messages.size() > MAX_MESSAGES) {
            messages.remove(0);
        }
        Files.createDirectories(directory);
        Path target = conversationFile(sender, recipient);
        Path temporary = Files.createTempFile(directory, "history-", ".tmp");
        try {
            // 每次写一份最多 50 条的新文件，不能直接截断旧文件，以免写失败丢失旧记录。
            try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(temporary)))) {
                out.writeInt(FILE_MAGIC);
                out.writeInt(FILE_VERSION);
                out.writeInt(messages.size());
                for (PrivateMessage item : messages) {
                    item.writeTo(out);
                }
            }
            try {
                Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporary);
        }
        return message;
    }

    /** 文件不存在表示双方还没有记录；损坏或读取失败必须报告，不能当作空记录覆盖。 */
    public synchronized List<PrivateMessage> load(String first, String second) throws IOException {
        if (closed) {
            throw new IOException("服务端正在关闭，历史记录已停止读写");
        }
        Path file = conversationFile(first, second);
        List<PrivateMessage> messages = new ArrayList<>();
        if (Files.notExists(file)) {
            return messages;
        }
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(file)))) {
            if (in.readInt() != FILE_MAGIC || in.readInt() != FILE_VERSION) {
                throw new IOException("历史记录文件格式不正确");
            }
            int count = in.readInt();
            if (count < 0 || count > MAX_MESSAGES) {
                throw new IOException("历史记录条数不正确");
            }
            long previousId = 0;
            for (int i = 0; i < count; i++) {
                PrivateMessage message = PrivateMessage.readFrom(in);
                boolean samePair = (message.getSender().equals(first) && message.getRecipient().equals(second))
                        || (message.getSender().equals(second) && message.getRecipient().equals(first));
                if (!samePair || message.getId() <= previousId) {
                    throw new IOException("历史记录的会话或消息编号不正确");
                }
                messages.add(message);
                previousId = message.getId();
            }
            if (in.read() != -1) {
                throw new IOException("历史记录包含多余数据");
            }
        }
        return messages;
    }

    /** 先将双方昵称排序再生成固定文件名，A→B 与 B→A 共用文件，特殊字符不会成为路径。 */
    private Path conversationFile(String first, String second) throws IOException {
        String left = first.compareTo(second) <= 0 ? first : second;
        String right = first.compareTo(second) <= 0 ? second : first;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buffer);
        out.writeUTF(left);
        out.writeUTF(right);
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(buffer.toByteArray());
            return directory.resolve(HexFormat.of().formatHex(hash) + ".dat");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("当前 JDK 不支持 SHA-256", e);
        }
    }
}
