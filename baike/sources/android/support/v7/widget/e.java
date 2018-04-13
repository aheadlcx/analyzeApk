package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class e extends AnimatorListenerAdapter {
    final /* synthetic */ ActionBarOverlayLayout a;

    e(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.a = actionBarOverlayLayout;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.c = null;
        this.a.b = false;
    }

    public void onAnimationCancel(Animator animator) {
        this.a.c = null;
        this.a.b = false;
    }
}
