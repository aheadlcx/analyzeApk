package qsbk.app.activity.base;

class u implements Runnable {
    final /* synthetic */ s a;

    u(s sVar) {
        this.a = sVar;
    }

    public void run() {
        this.a.a.startAnimation(this.a.h);
        this.a.a.setVisibility(8);
    }
}
