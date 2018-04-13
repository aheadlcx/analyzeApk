package qsbk.app.im;

class cv implements Runnable {
    final /* synthetic */ ConversationActivity a;

    cv(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        this.a.g.removeSendingMsg(true);
    }
}
