package qsbk.app.im;

class bj implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ ChatMsgListenerWrapper c;

    bj(ChatMsgListenerWrapper chatMsgListenerWrapper, long j, int i) {
        this.c = chatMsgListenerWrapper;
        this.a = j;
        this.b = i;
    }

    public void run() {
        this.c.mProxy.onChatMsgStatusChanged(this.a, this.b);
    }
}
