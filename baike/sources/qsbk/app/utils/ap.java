package qsbk.app.utils;

class ap implements Runnable {
    final /* synthetic */ QiuyouCircleFragmentNotificationViewHelper a;

    ap(QiuyouCircleFragmentNotificationViewHelper qiuyouCircleFragmentNotificationViewHelper) {
        this.a = qiuyouCircleFragmentNotificationViewHelper;
    }

    public void run() {
        this.a.f.setDuration(200);
        this.a.g.startAnimation(this.a.f);
        this.a.g.setVisibility(8);
    }
}
