package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ev extends AnimatorListenerAdapter {
    final /* synthetic */ GuessBigOrSmallGameView a;

    ev(GuessBigOrSmallGameView guessBigOrSmallGameView) {
        this.a = guessBigOrSmallGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.aa.setVisibility(8);
        this.a.V.setVisibility(8);
    }
}
