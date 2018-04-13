package qsbk.app.im;

class ec implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    ec(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        this.a.am.setText(this.a.H());
        if (this.a.G()) {
            this.a.am.postDelayed(this, 1000);
        } else {
            this.a.F();
        }
    }
}
