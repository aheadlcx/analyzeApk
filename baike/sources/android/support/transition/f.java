package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;

class f extends AnimatorListenerAdapter {
    final /* synthetic */ ViewGroup a;
    final /* synthetic */ BitmapDrawable b;
    final /* synthetic */ View c;
    final /* synthetic */ float d;
    final /* synthetic */ ChangeBounds e;

    f(ChangeBounds changeBounds, ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
        this.e = changeBounds;
        this.a = viewGroup;
        this.b = bitmapDrawable;
        this.c = view;
        this.d = f;
    }

    public void onAnimationEnd(Animator animator) {
        bz.a(this.a).remove(this.b);
        bz.a(this.c, this.d);
    }
}
