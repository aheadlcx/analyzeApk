package qsbk.app.video;

class bh implements Runnable {
    final /* synthetic */ bg a;

    bh(bg bgVar) {
        this.a = bgVar;
    }

    public void run() {
        VideoPlayerView.b(this.a.a);
    }
}
