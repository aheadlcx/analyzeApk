package qsbk.app.activity;

class of implements Runnable {
    final /* synthetic */ GuideActivity a;

    of(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            this.a.finish();
        }
    }
}
