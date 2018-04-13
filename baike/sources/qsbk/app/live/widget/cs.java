package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class cs extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ Runnable b;
    final /* synthetic */ GameView c;

    cs(GameView gameView, boolean z, Runnable runnable) {
        this.c = gameView;
        this.a = z;
        this.b = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.a) {
            this.c.i();
        }
        if (this.b != null) {
            this.c.m.post(this.b);
        }
    }
}
