package qsbk.app.live.widget;

class db implements Runnable {
    final /* synthetic */ GameView a;

    db(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        this.a.m.removeCallbacks(this);
        this.a.b(this.a.u);
        this.a.b(this.a.t);
        this.a.b(this.a.v);
        this.a.m.postDelayed(this.a.S, 1600);
    }
}
