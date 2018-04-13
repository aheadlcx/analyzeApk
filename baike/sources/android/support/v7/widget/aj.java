package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

class aj extends AnimatorListenerAdapter {
    final /* synthetic */ a a;
    final /* synthetic */ ViewPropertyAnimator b;
    final /* synthetic */ View c;
    final /* synthetic */ DefaultItemAnimator d;

    aj(DefaultItemAnimator defaultItemAnimator, a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.d = defaultItemAnimator;
        this.a = aVar;
        this.b = viewPropertyAnimator;
        this.c = view;
    }

    public void onAnimationStart(Animator animator) {
        this.d.dispatchChangeStarting(this.a.newHolder, false);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setListener(null);
        this.c.setAlpha(1.0f);
        this.c.setTranslationX(0.0f);
        this.c.setTranslationY(0.0f);
        this.d.dispatchChangeFinished(this.a.newHolder, false);
        this.d.g.remove(this.a.newHolder);
        this.d.a();
    }
}
