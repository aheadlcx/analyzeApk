package qsbk.app.core.utils.websocket;

class i implements Runnable {
    final /* synthetic */ Exception a;
    final /* synthetic */ f b;

    i(f fVar, Exception exception) {
        this.b = fVar;
        this.a = exception;
    }

    public void run() {
        if (this.b.a.j != null) {
            this.b.a.j.onError(this.a);
        }
    }
}
