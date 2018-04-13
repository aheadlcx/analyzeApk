package qsbk.app.activity.publish;

class bn implements Runnable {
    final /* synthetic */ PublishActivity a;

    bn(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void run() {
        if (this.a.X && !this.a.V) {
            this.a.b(this.a.U);
            this.a.R.setVisibility(0);
            this.a.W = true;
        } else if (!this.a.X) {
            this.a.J();
            this.a.R.setVisibility(8);
            this.a.W = true;
        }
    }
}
