package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ay extends AnimatorListenerAdapter {
    final /* synthetic */ ax a;

    ay(ax axVar) {
        this.a = axVar;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.a.a == animator) {
            this.a.a = null;
        }
    }
}
