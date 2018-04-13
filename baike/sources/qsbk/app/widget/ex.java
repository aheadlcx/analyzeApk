package qsbk.app.widget;

class ex implements Runnable {
    final /* synthetic */ TouchResponseProgressBar a;

    ex(TouchResponseProgressBar touchResponseProgressBar) {
        this.a = touchResponseProgressBar;
    }

    public void run() {
        int progress = this.a.getProgress() + 1;
        if (progress >= this.a.getMax()) {
            progress = this.a.getMax();
        } else {
            this.a.postDelayed(this, 10);
        }
        this.a.setProgress(progress);
    }
}
