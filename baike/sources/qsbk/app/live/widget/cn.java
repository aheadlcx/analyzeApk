package qsbk.app.live.widget;

import android.animation.AnimatorSet;
import android.widget.ImageView;

class cn implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ AnimatorSet b;
    final /* synthetic */ GameView c;

    cn(GameView gameView, ImageView imageView, AnimatorSet animatorSet) {
        this.c = gameView;
        this.a = imageView;
        this.b = animatorSet;
    }

    public void run() {
        this.c.addView(this.a);
        this.b.start();
    }
}
