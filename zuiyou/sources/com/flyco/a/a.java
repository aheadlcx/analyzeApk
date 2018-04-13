package com.flyco.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Interpolator;

public abstract class a {
    protected long a = 500;
    protected AnimatorSet b = new AnimatorSet();
    private Interpolator c;
    private long d;
    private a e;

    public interface a {
        void a(Animator animator);

        void b(Animator animator);

        void c(Animator animator);

        void d(Animator animator);
    }

    public abstract void a(View view);

    protected void b(View view) {
        c(view);
        a(view);
        this.b.setDuration(this.a);
        if (this.c != null) {
            this.b.setInterpolator(this.c);
        }
        if (this.d > 0) {
            this.b.setStartDelay(this.d);
        }
        if (this.e != null) {
            this.b.addListener(new AnimatorListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                    this.a.e.a(animator);
                }

                public void onAnimationRepeat(Animator animator) {
                    this.a.e.b(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    this.a.e.c(animator);
                }

                public void onAnimationCancel(Animator animator) {
                    this.a.e.d(animator);
                }
            });
        }
        this.b.start();
    }

    public static void c(View view) {
        view.setAlpha(1.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setRotation(0.0f);
        view.setRotationY(0.0f);
        view.setRotationX(0.0f);
    }

    public a a(long j) {
        this.a = j;
        return this;
    }

    public a a(a aVar) {
        this.e = aVar;
        return this;
    }

    public void d(View view) {
        b(view);
    }
}
