package qsbk.app.activity;

class fy implements Runnable {
    final /* synthetic */ CircleArticleActivity a;

    fy(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public void run() {
        if (this.a.getIntent().getBooleanExtra("showKeyboard", false)) {
            this.a.showKeyboard();
        }
    }
}
