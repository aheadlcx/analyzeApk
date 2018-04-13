package android.support.v4.media;

class z implements Runnable {
    final /* synthetic */ a a;

    z(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.f.b.remove(this.a.c.asBinder());
    }
}
