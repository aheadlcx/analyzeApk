package qsbk.app.utils;

class aq implements Runnable {
    final /* synthetic */ QiuyouCircleFragmentNotificationViewHelper a;

    aq(QiuyouCircleFragmentNotificationViewHelper qiuyouCircleFragmentNotificationViewHelper) {
        this.a = qiuyouCircleFragmentNotificationViewHelper;
    }

    public void run() {
        this.a.e.setDuration(200);
        this.a.g.startAnimation(this.a.e);
        this.a.g.setVisibility(0);
    }
}
