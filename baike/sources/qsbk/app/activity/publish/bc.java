package qsbk.app.activity.publish;

class bc implements Runnable {
    final /* synthetic */ PublishActivity a;

    bc(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void run() {
        this.a.I();
    }
}
