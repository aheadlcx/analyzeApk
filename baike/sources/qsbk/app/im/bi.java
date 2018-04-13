package qsbk.app.im;

class bi implements Runnable {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ ChatMsgListenerWrapper b;

    bi(ChatMsgListenerWrapper chatMsgListenerWrapper, ChatMsg chatMsg) {
        this.b = chatMsgListenerWrapper;
        this.a = chatMsg;
    }

    public void run() {
        this.b.mProxy.onGroupMessageReceived(this.a);
    }
}
