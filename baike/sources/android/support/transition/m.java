package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;

class m extends AnimatorListenerAdapter {
    final /* synthetic */ View a;
    final /* synthetic */ Rect b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;
    final /* synthetic */ ChangeBounds g;
    private boolean h;

    m(ChangeBounds changeBounds, View view, Rect rect, int i, int i2, int i3, int i4) {
        this.g = changeBounds;
        this.a = view;
        this.b = rect;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public void onAnimationCancel(Animator animator) {
        this.h = true;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.h) {
            ViewCompat.setClipBounds(this.a, this.b);
            bz.a(this.a, this.c, this.d, this.e, this.f);
        }
    }
}
