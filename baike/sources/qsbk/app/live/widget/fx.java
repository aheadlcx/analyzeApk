package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class fx extends AnimatorListenerAdapter {
    final /* synthetic */ NumberAnimTextView a;
    final /* synthetic */ View b;
    final /* synthetic */ fw c;

    fx(fw fwVar, NumberAnimTextView numberAnimTextView, View view) {
        this.c = fwVar;
        this.a = numberAnimTextView;
        this.b = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.setDuration(1500);
        this.a.setNumberString("0", this.c.c + "");
        this.c.e.postDelayed(new fy(this), 2000);
    }
}
