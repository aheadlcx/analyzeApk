package qsbk.app.im;

class jl implements Runnable {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ UserChatManager b;

    jl(UserChatManager userChatManager, ChatMsg chatMsg) {
        this.b = userChatManager;
        this.a = chatMsg;
    }

    public void run() {
        this.b.reSendFailMsg(this.a);
    }
}
