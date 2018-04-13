package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.util.ArrayMap;

class bg extends AnimatorListenerAdapter {
    final /* synthetic */ ArrayMap a;
    final /* synthetic */ Transition b;

    bg(Transition transition, ArrayMap arrayMap) {
        this.b = transition;
        this.a = arrayMap;
    }

    public void onAnimationStart(Animator animator) {
        this.b.B.add(animator);
    }

    public void onAnimationEnd(Animator animator) {
        this.a.remove(animator);
        this.b.B.remove(animator);
    }
}
