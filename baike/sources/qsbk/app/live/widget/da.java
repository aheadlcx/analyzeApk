package qsbk.app.live.widget;

class da implements Runnable {
    final /* synthetic */ GameView a;

    da(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        this.a.m.removeCallbacks(this);
        this.a.b(this.a.p);
        this.a.b(this.a.n);
        this.a.b(this.a.r);
        this.a.m.postDelayed(this.a.R, 1600);
    }
}
