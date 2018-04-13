package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ip extends AnimatorListenerAdapter {
    final /* synthetic */ float a;
    final /* synthetic */ in b;

    ip(in inVar, float f) {
        this.b = inVar;
        this.a = f;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.a == 0.0f) {
            this.b.a.e.setVisibility(0);
        }
    }
}
