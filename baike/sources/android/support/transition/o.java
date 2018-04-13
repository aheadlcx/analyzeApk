package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;

class o extends AnimatorListenerAdapter {
    final /* synthetic */ View a;
    final /* synthetic */ ChangeClipBounds b;

    o(ChangeClipBounds changeClipBounds, View view) {
        this.b = changeClipBounds;
        this.a = view;
    }

    public void onAnimationEnd(Animator animator) {
        ViewCompat.setClipBounds(this.a, null);
    }
}
