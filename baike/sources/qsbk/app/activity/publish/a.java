package qsbk.app.activity.publish;

class a implements Runnable {
    final /* synthetic */ CirclePublishActivity a;

    a(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void run() {
        if (!this.a.I) {
            this.a.q();
        }
        this.a.c.postDelayed(this, 15000);
    }
}
