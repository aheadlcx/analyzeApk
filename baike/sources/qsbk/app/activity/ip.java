package qsbk.app.activity;

class ip implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ CircleTopicActivity b;

    ip(CircleTopicActivity circleTopicActivity, String str) {
        this.b = circleTopicActivity;
        this.a = str;
    }

    public void run() {
        this.b.q();
        this.b.ac.setMessage(this.a);
        this.b.ac.show();
    }
}
