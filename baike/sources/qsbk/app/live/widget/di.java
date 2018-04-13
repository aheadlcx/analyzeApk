package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class di extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ Runnable b;
    final /* synthetic */ GameView c;

    di(GameView gameView, ImageView imageView, Runnable runnable) {
        this.c = gameView;
        this.a = imageView;
        this.b = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        this.c.removeView(this.a);
        if (this.b != null) {
            this.b.run();
        }
    }
}
