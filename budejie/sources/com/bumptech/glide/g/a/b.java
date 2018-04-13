package com.bumptech.glide.g.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

public class b<T extends Drawable> implements c<T> {
    private final c<T> a;
    private final int b;

    public b(c<T> cVar, int i) {
        this.a = cVar;
        this.b = i;
    }

    public boolean a(T t, c$a c_a) {
        if (c_a.b() != null) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{c_a.b(), t});
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(this.b);
            c_a.a(transitionDrawable);
            return true;
        }
        this.a.a(t, c_a);
        return false;
    }
}
