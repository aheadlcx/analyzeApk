package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewPropertyAnimator;

class af extends AnimatorListenerAdapter {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ ViewPropertyAnimator b;
    final /* synthetic */ View c;
    final /* synthetic */ DefaultItemAnimator d;

    af(DefaultItemAnimator defaultItemAnimator, ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.d = defaultItemAnimator;
        this.a = viewHolder;
        this.b = viewPropertyAnimator;
        this.c = view;
    }

    public void onAnimationStart(Animator animator) {
        this.d.dispatchRemoveStarting(this.a);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setListener(null);
        this.c.setAlpha(1.0f);
        this.d.dispatchRemoveFinished(this.a);
        this.d.f.remove(this.a);
        this.d.a();
    }
}
