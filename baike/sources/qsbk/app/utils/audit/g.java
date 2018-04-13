package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;

class g implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ int b;
    final /* synthetic */ ImageView c;
    final /* synthetic */ Drawable d;
    final /* synthetic */ SimpleImageLoader e;

    g(SimpleImageLoader simpleImageLoader, ProgressBar progressBar, int i, ImageView imageView, Drawable drawable) {
        this.e = simpleImageLoader;
        this.a = progressBar;
        this.b = i;
        this.c = imageView;
        this.d = drawable;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(0);
            this.a.setIndeterminate(false);
            this.a.setProgress((int) ((((double) this.b) * 0.07d) + 1.0d));
            this.a.setMax(this.b);
        }
        this.c.setImageDrawable(this.d);
    }
}
