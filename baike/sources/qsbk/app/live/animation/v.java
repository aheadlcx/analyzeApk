package qsbk.app.live.animation;

import android.animation.AnimatorSet;
import android.widget.ImageView;

class v implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ AnimatorSet b;
    final /* synthetic */ EvilAnimation c;

    v(EvilAnimation evilAnimation, ImageView imageView, AnimatorSet animatorSet) {
        this.c = evilAnimation;
        this.a = imageView;
        this.b = animatorSet;
    }

    public void run() {
        this.a.setVisibility(0);
        this.b.start();
    }
}
