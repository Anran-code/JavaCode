package com.ranan.gui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 群聊和私聊共用的记录区：自己靠右绿色，对方靠左蓝色。方法在事件线程中调用。 */
public class ChatMessagePane extends JTextPane {
    private static final Pattern MESSAGE_HEADER = Pattern.compile(
            "^(.*) \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(?: .*)?$");
    private final String nickname;

    /** 创建只读记录区，统一设置消息头和正文样式。 */
    public ChatMessagePane(String nickname) {
        this.nickname = nickname;
        setEditable(false);
        setFont(new Font("楷体", Font.PLAIN, 20));
        setBackground(new Color(225, 225, 225));
        setMargin(new Insets(12, 12, 12, 12));
        Style header = addStyle("header", null);
        StyleConstants.setFontFamily(header, "宋体");
        StyleConstants.setFontSize(header, 13);
        StyleConstants.setForeground(header, Color.DARK_GRAY);
        Style body = addStyle("body", null);
        StyleConstants.setFontFamily(body, "楷体");
        StyleConstants.setFontSize(body, 20);
        StyleConstants.setForeground(body, new Color(21, 101, 192));
        Style ownBody = addStyle("ownBody", body);
        StyleConstants.setForeground(ownBody, new Color(25, 135, 65));
    }

    /** 群聊按第一行完整昵称判断是否为自己发送。 */
    public void appendMessage(String message) {
        String header = message.replace("\r\n", "\n").split("\n", 2)[0];
        Matcher matcher = MESSAGE_HEADER.matcher(header);
        appendMessage(message, matcher.matches() && matcher.group(1).equals(nickname));
    }

    /** 私聊已经单独收到发送人信息，可以直接指定左右样式。 */
    public void appendMessage(String message, boolean ownMessage) {
        String text = message.replace("\r\n", "\n");
        int lineBreak = text.indexOf('\n');
        int alignment = ownMessage ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT;
        if (lineBreak >= 0) {
            appendText(text.substring(0, lineBreak) + "\n", "header", alignment);
            text = text.substring(lineBreak + 1);
        }
        appendText(text + "\n\n", ownMessage ? "ownBody" : "body", alignment);
    }

    /** 系统提示使用小号灰色文字，固定左对齐。 */
    public void appendNotice(String text) {
        appendText("系统提示：" + text + "\n", "header", StyleConstants.ALIGN_LEFT);
    }

    /** 追加纯文本并设置段落对齐，保留多行正文且不影响下一条消息。 */
    private void appendText(String text, String style, int alignment) {
        StyledDocument document = getStyledDocument();
        try {
            int start = document.getLength();
            document.insertString(start, text, getStyle(style));
            SimpleAttributeSet paragraph = new SimpleAttributeSet();
            StyleConstants.setAlignment(paragraph, alignment);
            StyleConstants.setRightIndent(paragraph, 12);
            document.setParagraphAttributes(start, Math.max(0, text.length() - 1), paragraph, false);
            setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            throw new IllegalStateException("聊天记录插入位置错误", e);
        }
    }
}
