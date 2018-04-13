package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class cr extends AnimatorListenerAdapter {
    final /* synthetic */ GameBetView a;
    final /* synthetic */ View b;
    final /* synthetic */ GameView c;

    cr(GameView gameView, GameBetView gameBetView, View view) {
        this.c = gameView;
        this.a = gameBetView;
        this.b = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.c.a(this.a);
        this.c.a(this.b);
    }
}
