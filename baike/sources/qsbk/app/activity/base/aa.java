package qsbk.app.activity.base;

class aa implements Runnable {
    final /* synthetic */ BaseEmotionActivity a;

    aa(BaseEmotionActivity baseEmotionActivity) {
        this.a = baseEmotionActivity;
    }

    public void run() {
        this.a.r();
        this.a.a(0);
    }
}
