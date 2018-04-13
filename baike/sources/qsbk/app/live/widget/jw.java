package qsbk.app.live.widget;

class jw implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ boolean b;
    final /* synthetic */ YPDXGameView c;

    jw(YPDXGameView yPDXGameView, long j, boolean z) {
        this.c = yPDXGameView;
        this.a = j;
        this.b = z;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}
