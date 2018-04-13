package qsbk.app.activity.publish;

class ag implements Runnable {
    final /* synthetic */ CirclePublishActivity a;

    ag(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void run() {
        if (this.a.O && !this.a.V) {
            this.a.c(this.a.K);
            this.a.s.setVisibility(0);
            this.a.U = true;
        } else if (!this.a.O) {
            this.a.F();
            this.a.s.setVisibility(8);
            this.a.c(0);
            this.a.U = true;
        }
    }
}
