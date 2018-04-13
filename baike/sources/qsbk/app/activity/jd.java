package qsbk.app.activity;

class jd implements Runnable {
    final /* synthetic */ jc a;

    jd(jc jcVar) {
        this.a = jcVar;
    }

    public void run() {
        this.a.a.f = true;
        this.a.a.a.fullScroll(17);
    }
}
