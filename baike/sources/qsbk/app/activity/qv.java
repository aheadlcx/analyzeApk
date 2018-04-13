package qsbk.app.activity;

class qv implements Runnable {
    final /* synthetic */ LaiseeGetActivity a;

    qv(LaiseeGetActivity laiseeGetActivity) {
        this.a = laiseeGetActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            this.a.c.setCanceledOnTouchOutside(true);
        }
    }
}
