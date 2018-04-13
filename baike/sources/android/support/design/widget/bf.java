package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bf extends AnimatorListenerAdapter {
    final /* synthetic */ CharSequence a;
    final /* synthetic */ TextInputLayout b;

    bf(TextInputLayout textInputLayout, CharSequence charSequence) {
        this.b = textInputLayout;
        this.a = charSequence;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.b.setText(this.a);
        this.b.b.setVisibility(4);
    }
}
