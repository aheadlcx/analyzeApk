package qsbk.app.im;

class bk implements Runnable {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ ChatMsgListenerWrapper b;

    bk(ChatMsgListenerWrapper chatMsgListenerWrapper, ChatMsg chatMsg) {
        this.b = chatMsgListenerWrapper;
        this.a = chatMsg;
    }

    public void run() {
        this.b.mProxy.onDuplicateConnect(this.a);
    }
}
