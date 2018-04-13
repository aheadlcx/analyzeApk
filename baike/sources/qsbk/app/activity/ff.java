package qsbk.app.activity;

class ff implements Runnable {
    final /* synthetic */ CircleArticleActivity a;

    ff(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void run() {
        this.a.showLoading();
    }
}
