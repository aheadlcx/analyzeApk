package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewPropertyAnimator;

class ag extends AnimatorListenerAdapter {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ View b;
    final /* synthetic */ ViewPropertyAnimator c;
    final /* synthetic */ DefaultItemAnimator d;

    ag(DefaultItemAnimator defaultItemAnimator, ViewHolder viewHolder, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.d = defaultItemAnimator;
        this.a = viewHolder;
        this.b = view;
        this.c = viewPropertyAnimator;
    }

    public void onAnimationStart(Animator animator) {
        this.d.dispatchAddStarting(this.a);
    }

    public void onAnimationCancel(Animator animator) {
        this.b.setAlpha(1.0f);
    }

    public void onAnimationEnd(Animator animator) {
        this.c.setListener(null);
        this.d.dispatchAddFinished(this.a);
        this.d.d.remove(this.a);
        this.d.a();
    }
}
