package qsbk.app.activity;

class fv implements Runnable {
    final /* synthetic */ CircleArticleActivity a;

    fv(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void run() {
        this.a.hideLoading();
    }
}
