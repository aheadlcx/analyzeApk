package qsbk.app.video;

class b implements Runnable {
    int a = 0;
    final /* synthetic */ CircleVideoPlayerView b;

    b(CircleVideoPlayerView circleVideoPlayerView) {
        this.b = circleVideoPlayerView;
    }

    public void run() {
        this.b.removeCallbacks(this);
        int currentTime = (int) this.b.getCurrentTime();
        int duration = (int) this.b.e.getDuration();
        int i = currentTime / 1000;
        int i2 = i / 60;
        i %= 60;
        this.b.n.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
        if (!this.b.q) {
            this.b.p.setProgress(currentTime);
        }
        if (currentTime + 3000 >= duration && this.a + 3000 < duration) {
            this.b.showControlBar(false, true);
        }
        this.a = currentTime;
        this.b.postDelayed(this, 250);
    }
}
