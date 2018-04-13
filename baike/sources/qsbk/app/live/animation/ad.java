package qsbk.app.live.animation;

class ad implements Runnable {
    final /* synthetic */ ShipAnimation a;

    ad(ShipAnimation shipAnimation) {
        this.a = shipAnimation;
    }

    public void run() {
        this.a.b();
        if (this.a.o >= -40) {
            this.a.o = this.a.o - 20;
            this.a.a(this.a.m, 20);
            this.a.a(this.a.n, 20);
            this.a.h();
            return;
        }
        this.a.o = 0;
        this.a.a();
    }
}
