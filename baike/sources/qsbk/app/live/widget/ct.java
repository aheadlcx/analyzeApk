package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ct extends AnimatorListenerAdapter {
    final /* synthetic */ Runnable a;
    final /* synthetic */ GameView b;

    ct(GameView gameView, Runnable runnable) {
        this.b = gameView;
        this.a = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.z.setEnabled(true);
        if (this.a != null) {
            this.b.m.post(this.a);
        }
    }
}
