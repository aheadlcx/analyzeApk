package android.support.v4.provider;

class s implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ r b;

    s(r rVar, Object obj) {
        this.b = rVar;
        this.a = obj;
    }

    public void run() {
        this.b.c.onReply(this.a);
    }
}
