package qsbk.app.activity.base;

class t implements Runnable {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void run() {
        this.a.a.startAnimation(this.a.g);
        this.a.a.setVisibility(0);
    }
}
