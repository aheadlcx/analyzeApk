package android.support.v4.widget;

class d implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar a;

    d(ContentLoadingProgressBar contentLoadingProgressBar) {
        this.a = contentLoadingProgressBar;
    }

    public void run() {
        this.a.b = false;
        this.a.a = -1;
        this.a.setVisibility(8);
    }
}
