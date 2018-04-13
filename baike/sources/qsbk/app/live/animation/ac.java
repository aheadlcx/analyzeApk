package qsbk.app.live.animation;

class ac implements Runnable {
    final /* synthetic */ ShipAnimation a;

    ac(ShipAnimation shipAnimation) {
        this.a = shipAnimation;
    }

    public void run() {
        this.a.g();
    }
}
