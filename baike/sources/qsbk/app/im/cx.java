package qsbk.app.im;

class cx implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ ConversationActivity b;

    cx(ConversationActivity conversationActivity, long j) {
        this.b = conversationActivity;
        this.a = j;
    }

    public void run() {
        this.b.aj.deleteMessagesWith(this.a);
        this.b.y.post(new cy(this));
    }
}
