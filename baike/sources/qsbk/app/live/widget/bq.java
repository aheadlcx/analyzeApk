package qsbk.app.live.widget;

class bq implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ GameFanfanleProgressView b;

    bq(GameFanfanleProgressView gameFanfanleProgressView, int i) {
        this.b = gameFanfanleProgressView;
        this.a = i;
    }

    public void run() {
        this.b.b.setProgress(this.a);
    }
}
