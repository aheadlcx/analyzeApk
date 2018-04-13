package qsbk.app.utils.audit;

import android.widget.ProgressBar;

class h implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ SimpleImageLoader d;

    h(SimpleImageLoader simpleImageLoader, ProgressBar progressBar, int i, int i2) {
        this.d = simpleImageLoader;
        this.a = progressBar;
        this.b = i;
        this.c = i2;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(0);
            this.a.setIndeterminate(false);
            if (this.b < this.c) {
                this.a.setProgress(this.c);
            } else {
                this.a.setProgress(this.b);
            }
        }
    }
}
