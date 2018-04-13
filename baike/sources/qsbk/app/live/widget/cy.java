package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class cy extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ GameView b;

    cy(GameView gameView, ImageView imageView) {
        this.b = gameView;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.a != null) {
            this.b.a(this.a);
        }
    }
}
