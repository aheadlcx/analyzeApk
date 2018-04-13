package qsbk.app.live.animation;

import android.widget.ImageView;

class f implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ CarAnimation b;

    f(CarAnimation carAnimation, ImageView imageView) {
        this.b = carAnimation;
        this.a = imageView;
    }

    public void run() {
        this.b.f(this.a);
        this.b.b(this.a);
    }
}
