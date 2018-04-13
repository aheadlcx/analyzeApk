package qsbk.app.activity.publish;

class bm implements Runnable {
    final /* synthetic */ PublishActivity a;

    bm(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void run() {
        this.a.y.getLocation(this.a);
    }
}
