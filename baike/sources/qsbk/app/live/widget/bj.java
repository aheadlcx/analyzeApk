package qsbk.app.live.widget;

class bj implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ FavorLayout b;

    bj(FavorLayout favorLayout, int i) {
        this.b = favorLayout;
        this.a = i;
    }

    public void run() {
        this.b.addFavor(this.a);
    }
}
