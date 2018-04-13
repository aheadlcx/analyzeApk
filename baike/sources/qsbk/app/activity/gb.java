package qsbk.app.activity;

class gb implements Runnable {
    final /* synthetic */ CircleArticleActivity a;

    gb(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void run() {
        this.a.i();
        this.a.getMainUIHandler().postDelayed(this, 60000);
    }
}
