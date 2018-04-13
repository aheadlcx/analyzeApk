package qsbk.app.core.widget;

class af implements Runnable {
    final /* synthetic */ ae a;

    af(ae aeVar) {
        this.a = aeVar;
    }

    public void run() {
        this.a.a.i = this.a.a.j = this.a.a.k;
        this.a.a.postInvalidate();
    }
}
