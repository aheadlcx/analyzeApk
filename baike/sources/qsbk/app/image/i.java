package qsbk.app.image;

class i implements Runnable {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public void run() {
        this.a.a.cancel();
    }
}
