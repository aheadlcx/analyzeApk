package qsbk.app.live.widget;

class df implements Runnable {
    final /* synthetic */ GameView a;

    df(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        this.a.h.setVisibility(0);
        this.a.e(this.a.h);
    }
}
