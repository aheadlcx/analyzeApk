package android.support.v4.provider;

class l implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ e b;

    l(e eVar, int i) {
        this.b = eVar;
        this.a = i;
    }

    public void run() {
        this.b.d.onTypefaceRequestFailed(this.a);
    }
}
