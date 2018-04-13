package cn.v6.sixrooms.bean;

public class ChatPermissionBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private int chatType;

    public int getChatType() {
        return this.chatType;
    }

    public void setChatType(int i) {
        this.chatType = i;
    }
}
