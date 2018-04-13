package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class cu extends AnimatorListenerAdapter {
    final /* synthetic */ GameView a;

    cu(GameView gameView) {
        this.a = gameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.a(this.a.A);
    }
}
