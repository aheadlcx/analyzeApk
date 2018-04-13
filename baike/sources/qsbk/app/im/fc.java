package qsbk.app.im;

class fc implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    fc(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        this.a.g.removeSendingMsg(true);
    }
}
