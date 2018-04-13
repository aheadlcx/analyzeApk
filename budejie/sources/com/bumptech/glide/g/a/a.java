package com.bumptech.glide.g.a;

import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class a<T extends Drawable> implements d<T> {
    private final g<T> a;
    private final int b;
    private b<T> c;
    private b<T> d;

    private static class a implements a {
        private final int a;

        a(int i) {
            this.a = i;
        }

        public Animation a() {
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) this.a);
            return alphaAnimation;
        }
    }

    public a() {
        this(300);
    }

    public a(int i) {
        this(new g(new a(i)), i);
    }

    a(g<T> gVar, int i) {
        this.a = gVar;
        this.b = i;
    }

    public c<T> a(boolean z, boolean z2) {
        if (z) {
            return e.b();
        }
        if (z2) {
            return a();
        }
        return b();
    }

    private c<T> a() {
        if (this.c == null) {
            this.c = new b(this.a.a(false, true), this.b);
        }
        return this.c;
    }

    private c<T> b() {
        if (this.d == null) {
            this.d = new b(this.a.a(false, false), this.b);
        }
        return this.d;
    }
}
