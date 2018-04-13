package qsbk.app.im;

class bh implements Runnable {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ ChatMsgListenerWrapper b;

    bh(ChatMsgListenerWrapper chatMsgListenerWrapper, ChatMsg chatMsg) {
        this.b = chatMsgListenerWrapper;
        this.a = chatMsg;
    }

    public void run() {
        this.b.mProxy.onMessageReceived(this.a);
    }
}
