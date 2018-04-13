package com.flyco.dialog.c.a;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;

public abstract class b<T extends b<T>> extends c<T> {
    private com.flyco.a.a u;
    private com.flyco.a.a v;

    private class a extends com.flyco.a.a {
        final /* synthetic */ b c;

        private a(b bVar) {
            this.c = bVar;
        }

        public void a(View view) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 0.9f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, 0.9f});
            this.b.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    private class b extends com.flyco.a.a {
        final /* synthetic */ b c;

        private b(b bVar) {
            this.c = bVar;
        }

        public void a(View view) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{0.9f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.9f, 1.0f});
            this.b.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    public b(Context context, View view) {
        super(context);
        this.a = view;
        this.l = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.m = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
    }

    public b(Context context) {
        this(context, null);
    }

    protected void onStart() {
        super.onStart();
        this.h.setLayoutParams(new LayoutParams(-1, -1));
        this.h.setGravity(80);
        getWindow().setGravity(80);
        this.h.setPadding(this.q, this.r, this.s, this.t);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        f();
    }

    public void dismiss() {
        g();
    }

    protected com.flyco.a.a d() {
        if (this.u == null) {
            this.u = new a();
        }
        return this.u;
    }

    protected com.flyco.a.a e() {
        if (this.v == null) {
            this.v = new b();
        }
        return this.v;
    }
}
