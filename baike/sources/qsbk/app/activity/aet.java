package qsbk.app.activity;

class aet implements Runnable {
    final /* synthetic */ VideoImmersionActivity a;

    aet(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void run() {
        try {
            this.a.a(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
