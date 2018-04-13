package android.support.v4.provider;

class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        this.a.d.onTypefaceRequestFailed(-1);
    }
}
