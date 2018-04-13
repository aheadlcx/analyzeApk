package qsbk.app.live.widget;

class gu implements Runnable {
    final /* synthetic */ gt a;

    gu(gt gtVar) {
        this.a = gtVar;
    }

    public void run() {
        this.a.a.start();
        this.a.b.start();
    }
}
