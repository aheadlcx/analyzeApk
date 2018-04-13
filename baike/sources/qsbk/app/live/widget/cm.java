package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class cm extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ GameView b;

    cm(GameView gameView, ImageView imageView) {
        this.b = gameView;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.removeView(this.a);
    }
}
