package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ik extends AnimatorListenerAdapter {
    final /* synthetic */ RollTableView a;

    ik(RollTableView rollTableView) {
        this.a = rollTableView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.i = 0;
    }
}
