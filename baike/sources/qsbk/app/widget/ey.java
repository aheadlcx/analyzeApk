package qsbk.app.widget;

class ey implements Runnable {
    final /* synthetic */ TouchResponseProgressBar a;

    ey(TouchResponseProgressBar touchResponseProgressBar) {
        this.a = touchResponseProgressBar;
    }

    public void run() {
        int progress = this.a.getProgress() - 1;
        if (progress <= 0) {
            progress = 0;
        } else {
            this.a.postDelayed(this, 16);
        }
        this.a.setProgress(progress);
    }
}
