package qsbk.app.live.widget;

class dg implements Runnable {
    final /* synthetic */ GameView a;

    dg(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        if (this.a.j != null) {
            this.a.j.setVisibility(0);
            this.a.e(this.a.j);
        }
    }
}
