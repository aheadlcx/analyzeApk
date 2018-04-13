package qsbk.app.live.widget;

class co implements Runnable {
    final /* synthetic */ GameView a;

    co(GameView gameView) {
        this.a = gameView;
    }

    public void run() {
        this.a.c.setVisibility(4);
        this.a.d.setVisibility(0);
        this.a.h();
        this.a.setBetEnable(true);
    }
}
