package com.flyco.dialog.c.a;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.flyco.a.a;

public abstract class c<T extends c<T>> extends a<T> {
    protected View a;
    protected Animation l;
    protected Animation m;
    protected long n = 350;
    protected boolean o;
    protected boolean p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    private a u;
    private a v;

    protected abstract a d();

    protected abstract a e();

    public c(Context context) {
        super(context);
    }

    protected void f() {
        if (this.l != null) {
            this.l.setDuration(this.n);
            this.l.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                    this.a.o = true;
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.o = false;
                }
            });
            this.i.startAnimation(this.l);
        }
        if (this.a != null) {
            if (d() != null) {
                this.u = d();
            }
            this.u.a(this.n).d(this.a);
        }
    }

    protected void g() {
        if (this.m != null) {
            this.m.setDuration(this.n);
            this.m.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                    this.a.p = true;
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.p = false;
                    this.a.c();
                }
            });
            this.i.startAnimation(this.m);
        } else {
            c();
        }
        if (this.a != null) {
            if (e() != null) {
                this.v = e();
            }
            this.v.a(this.n).d(this.a);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.p || this.o) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onBackPressed() {
        if (!this.p && !this.o) {
            super.onBackPressed();
        }
    }
}
