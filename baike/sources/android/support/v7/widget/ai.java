package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

class ai extends AnimatorListenerAdapter {
    final /* synthetic */ a a;
    final /* synthetic */ ViewPropertyAnimator b;
    final /* synthetic */ View c;
    final /* synthetic */ DefaultItemAnimator d;

    ai(DefaultItemAnimator defaultItemAnimator, a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.d = defaultItemAnimator;
        this.a = aVar;
        this.b = viewPropertyAnimator;
        this.c = view;
    }

    public void onAnimationStart(Animator animator) {
        this.d.dispatchChangeStarting(this.a.oldHolder, true);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setListener(null);
        this.c.setAlpha(1.0f);
        this.c.setTranslationX(0.0f);
        this.c.setTranslationY(0.0f);
        this.d.dispatchChangeFinished(this.a.oldHolder, true);
        this.d.g.remove(this.a.oldHolder);
        this.d.a();
    }
}
