package qsbk.app.im;

public interface ISyncChatMsgListener {
    void onSyncMsgControl(ChatMsg chatMsg);

    void onSyncMsgMaintenance(ChatMsg chatMsg);

    void onSyncMsgReceived(ChatMsg chatMsg);
}
