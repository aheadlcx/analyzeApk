package qsbk.app.core.utils.websocket;

class h implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    h(f fVar, int i, String str) {
        this.c = fVar;
        this.a = i;
        this.b = str;
    }

    public void run() {
        if (this.c.a.j != null) {
            this.c.a.j.onDisconnect(this.a, this.b);
        }
    }
}
