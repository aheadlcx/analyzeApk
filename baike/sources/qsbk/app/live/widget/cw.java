package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class cw extends AnimatorListenerAdapter {
    final /* synthetic */ GameView a;

    cw(GameView gameView) {
        this.a = gameView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.k.setEnabled(true);
        this.a.k.setVisibility(4);
    }
}
