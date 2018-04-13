package qsbk.app.live.widget;

import android.animation.AnimatorSet;
import android.widget.ImageView;

class n implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ AnimatorSet b;
    final /* synthetic */ CatAndDogGameView c;

    n(CatAndDogGameView catAndDogGameView, ImageView imageView, AnimatorSet animatorSet) {
        this.c = catAndDogGameView;
        this.a = imageView;
        this.b = animatorSet;
    }

    public void run() {
        this.c.addView(this.a);
        this.b.start();
    }
}
