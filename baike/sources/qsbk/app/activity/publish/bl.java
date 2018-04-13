package qsbk.app.activity.publish;

class bl implements Runnable {
    final /* synthetic */ PublishActivity a;

    bl(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void run() {
        if (this.a.r() && this.a.Z != null && this.a.Z.isShowing()) {
            this.a.l.postDelayed(this, 15000);
        } else {
            this.a.l.postDelayed(this, 15000);
        }
    }
}
