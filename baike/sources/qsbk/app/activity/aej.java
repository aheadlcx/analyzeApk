package qsbk.app.activity;

class aej implements Runnable {
    final /* synthetic */ VideoFullScreenActivity a;

    aej(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void run() {
        if (VideoFullScreenActivity.a(this.a) == 0) {
            this.a.finish();
            return;
        }
        VideoFullScreenActivity.b(this.a);
        VideoFullScreenActivity.d(this.a).setText(String.format(VideoFullScreenActivity.c(this.a), new Object[]{Integer.valueOf(VideoFullScreenActivity.a(this.a))}));
        this.a.getMainUIHandler().postDelayed(VideoFullScreenActivity.e(this.a), 1000);
    }
}
