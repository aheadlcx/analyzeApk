package qsbk.app.video;

class a implements Runnable {
    final /* synthetic */ CircleVideoPlayerView a;

    a(CircleVideoPlayerView circleVideoPlayerView) {
        this.a = circleVideoPlayerView;
    }

    public void run() {
        this.a.hideControlBar();
    }
}
