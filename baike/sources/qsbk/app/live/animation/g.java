package qsbk.app.live.animation;

import android.widget.ImageView;

class g implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ CarAnimation b;

    g(CarAnimation carAnimation, ImageView imageView) {
        this.b = carAnimation;
        this.a = imageView;
    }

    public void run() {
        this.b.g(this.a);
        this.b.c(this.a);
    }
}
