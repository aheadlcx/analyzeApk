package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class ci extends AnimatorListenerAdapter {
    final /* synthetic */ bq a;
    final /* synthetic */ View b;
    final /* synthetic */ Visibility c;

    ci(Visibility visibility, bq bqVar, View view) {
        this.c = visibility;
        this.a = bqVar;
        this.b = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.remove(this.b);
    }
}
