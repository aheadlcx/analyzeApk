package qsbk.app.live.animation;

class ae implements Runnable {
    final /* synthetic */ ShipAnimation a;

    ae(ShipAnimation shipAnimation) {
        this.a = shipAnimation;
    }

    public void run() {
        this.a.b();
        if (this.a.o <= (this.a.e * 2) / 5) {
            this.a.o = this.a.o + 20;
            this.a.a(this.a.m, -20);
            this.a.a(this.a.n, -20);
            this.a.i();
            return;
        }
        this.a.j();
    }
}
