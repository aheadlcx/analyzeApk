package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;

class f implements Runnable {
    final /* synthetic */ ProgressBar a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ Drawable c;
    final /* synthetic */ SimpleImageLoader d;

    f(SimpleImageLoader simpleImageLoader, ProgressBar progressBar, ImageView imageView, Drawable drawable) {
        this.d = simpleImageLoader;
        this.a = progressBar;
        this.b = imageView;
        this.c = drawable;
    }

    public void run() {
        if (this.a != null) {
            this.a.setVisibility(4);
        }
        this.b.setImageDrawable(this.c);
    }
}
