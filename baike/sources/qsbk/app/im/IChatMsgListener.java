package qsbk.app.im;

public interface IChatMsgListener {
    public static final int AUTO_DISCONNECT = 6;
    public static final int CONNECTED = 2;
    public static final int CONNECTING = 1;
    public static final int CONNECT_AUTH_FAILED = 5;
    public static final int CONNECT_LOST = 3;
    public static final int RECONNECTING = 4;
    public static final String[] connectString = new String[]{"", "连接中..", "连接成功", "连接断开", "重连中..", "登录验证错误", "主动断开连接"};

    void onChatMsgStatusChanged(long j, int i);

    void onConnectStatusChange(int i);

    void onDuplicateConnect(ChatMsg chatMsg);

    void onGroupMessageReceived(ChatMsg chatMsg);

    void onMessageReceived(ChatMsg chatMsg);
}
