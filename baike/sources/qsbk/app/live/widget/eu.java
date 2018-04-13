package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class eu extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ GuessBigOrSmallGameView b;

    eu(GuessBigOrSmallGameView guessBigOrSmallGameView, boolean z) {
        this.b = guessBigOrSmallGameView;
        this.a = z;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.T.setVisibility(0);
        this.b.U.setVisibility(8);
        this.b.W.setVisibility(8);
        this.b.V.setVisibility(8);
        if (this.a) {
            this.b.f();
        }
    }
}
