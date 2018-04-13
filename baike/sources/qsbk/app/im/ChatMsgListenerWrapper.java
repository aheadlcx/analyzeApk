package qsbk.app.im;

import android.os.Handler;

public class ChatMsgListenerWrapper implements IChatMsgListener {
    public Handler mHandler;
    public IChatMsgListener mProxy;

    public ChatMsgListenerWrapper(IChatMsgListener iChatMsgListener, Handler handler) {
        this.mProxy = iChatMsgListener;
        this.mHandler = handler;
    }

    public void onMessageReceived(ChatMsg chatMsg) {
        this.mHandler.post(new bh(this, chatMsg));
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
        this.mHandler.post(new bi(this, chatMsg));
    }

    public void onChatMsgStatusChanged(long j, int i) {
        this.mHandler.post(new bj(this, j, i));
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
        this.mHandler.post(new bk(this, chatMsg));
    }

    public void onConnectStatusChange(int i) {
        this.mHandler.post(new bl(this, i));
    }
}
