package qsbk.app.live.animation;

class af implements Runnable {
    final /* synthetic */ ShipAnimation a;

    af(ShipAnimation shipAnimation) {
        this.a = shipAnimation;
    }

    public void run() {
        this.a.b();
        this.a.k = this.a.k + 5;
        this.a.l = this.a.l + 5;
        if (this.a.k >= this.a.m.length) {
            this.a.k = 0;
        }
        if (this.a.l >= this.a.n.length) {
            this.a.l = 0;
        }
        this.a.j();
    }
}
