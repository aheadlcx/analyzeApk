package android.support.v4.media;

class ai implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ g b;

    ai(g gVar, h hVar) {
        this.b = gVar;
        this.a = hVar;
    }

    public void run() {
        a aVar = (a) this.b.a.b.remove(this.a.asBinder());
        if (aVar != null) {
            aVar.c.asBinder().unlinkToDeath(aVar, 0);
        }
    }
}
