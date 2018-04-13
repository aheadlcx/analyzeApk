package qsbk.app.core.utils.websocket;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        if (this.a.a.j != null) {
            this.a.a.j.onConnect();
        }
    }
}
