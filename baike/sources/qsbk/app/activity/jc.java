package qsbk.app.activity;

class jc implements Runnable {
    final /* synthetic */ CircleTopicCategoriesActivity a;

    jc(CircleTopicCategoriesActivity circleTopicCategoriesActivity) {
        this.a = circleTopicCategoriesActivity;
    }

    public void run() {
        this.a.a.setSmoothScrollingEnabled(false);
        this.a.a.fullScroll(66);
        this.a.a.setSmoothScrollingEnabled(true);
        this.a.a.postDelayed(new jd(this), 300);
    }
}
