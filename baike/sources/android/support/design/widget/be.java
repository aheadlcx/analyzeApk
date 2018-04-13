package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class be extends AnimatorListenerAdapter {
    final /* synthetic */ TextInputLayout a;

    be(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public void onAnimationStart(Animator animator) {
        this.a.b.setVisibility(0);
    }
}
