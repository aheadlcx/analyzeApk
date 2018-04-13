package qsbk.app.im;

class bl implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ChatMsgListenerWrapper b;

    bl(ChatMsgListenerWrapper chatMsgListenerWrapper, int i) {
        this.b = chatMsgListenerWrapper;
        this.a = i;
    }

    public void run() {
        this.b.mProxy.onConnectStatusChange(this.a);
    }
}
