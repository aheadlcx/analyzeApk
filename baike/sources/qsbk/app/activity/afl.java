package qsbk.app.activity;

class afl implements Runnable {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afl(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void run() {
        try {
            this.a.a(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
