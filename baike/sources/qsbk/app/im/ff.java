package qsbk.app.im;

class ff implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ GroupConversationActivity b;

    ff(GroupConversationActivity groupConversationActivity, long j) {
        this.b = groupConversationActivity;
        this.a = j;
    }

    public void run() {
        this.b.af.deleteMessagesWith(this.a);
        this.b.y.post(new fg(this));
    }
}
