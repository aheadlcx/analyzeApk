package cn.xiaochuankeji.tieba.ui.widget.overscroll;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

abstract class e implements OnTouchListener, a {
    private final f a = new f();
    private final cn.xiaochuankeji.tieba.ui.widget.overscroll.a.a b;
    private final d c;
    private final g d;
    private final b e;
    private c f;
    private b g = new cn.xiaochuankeji.tieba.ui.widget.overscroll.d.a();
    private c h = new cn.xiaochuankeji.tieba.ui.widget.overscroll.d.b();
    private float i;

    static abstract class a {
        Property<View, Float> a;
        float b;
        float c;

        protected abstract void a(View view);

        a() {
        }
    }

    interface c {
        int a();

        void a(c cVar);

        boolean a(MotionEvent motionEvent);

        boolean b(MotionEvent motionEvent);
    }

    private class b implements AnimatorListener, AnimatorUpdateListener, c {
        final Interpolator a = new DecelerateInterpolator();
        final float b;
        final float c;
        final a d;
        final /* synthetic */ e e;

        b(e eVar, float f) {
            this.e = eVar;
            this.b = f;
            this.c = 0.5f * f;
            this.d = eVar.d();
        }

        public int a() {
            return 3;
        }

        public void a(c cVar) {
            this.e.g.a(this.e, cVar.a(), a());
            Animator b = b();
            b.addListener(this);
            b.start();
        }

        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        public boolean b(MotionEvent motionEvent) {
            return true;
        }

        public void onAnimationEnd(Animator animator) {
            this.e.a(this.e.c);
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.e.h.a(this.e, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        Animator b() {
            float f = 0.0f;
            View c = this.e.b.c();
            this.d.a(c);
            if (this.e.i == 0.0f || ((this.e.i < 0.0f && this.e.a.c) || (this.e.i > 0.0f && !this.e.a.c))) {
                return a(this.d.b);
            }
            float f2 = (-this.e.i) / this.b;
            if (f2 >= 0.0f) {
                f = f2;
            }
            f2 = (((-this.e.i) * this.e.i) / this.c) + this.d.b;
            ObjectAnimator a = a(c, (int) f, f2);
            ObjectAnimator a2 = a(f2);
            Animator animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{a, a2});
            return animatorSet;
        }

        ObjectAnimator a(View view, int i, float f) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, this.d.a, new float[]{f});
            ofFloat.setDuration((long) i);
            ofFloat.setInterpolator(this.a);
            ofFloat.addUpdateListener(this);
            return ofFloat;
        }

        ObjectAnimator a(float f) {
            float abs = (Math.abs(f) / this.d.c) * 800.0f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e.b.c(), this.d.a, new float[]{this.e.a.b});
            ofFloat.setDuration((long) Math.max((int) abs, 200));
            ofFloat.setInterpolator(this.a);
            ofFloat.addUpdateListener(this);
            return ofFloat;
        }
    }

    private class d implements c {
        final e a;
        final /* synthetic */ e b;

        d(e eVar) {
            this.b = eVar;
            this.a = eVar.c();
        }

        public int a() {
            return 0;
        }

        public boolean a(MotionEvent motionEvent) {
            if (!this.a.a(this.b.b.c(), motionEvent)) {
                return false;
            }
            if ((!this.b.b.a() || !this.a.c) && (!this.b.b.b() || this.a.c)) {
                return false;
            }
            this.b.a.a = motionEvent.getPointerId(0);
            this.b.a.b = this.a.a;
            this.b.a.c = this.a.c;
            this.b.a(this.b.d);
            return this.b.d.a(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            return false;
        }

        public void a(c cVar) {
            this.b.g.a(this.b, cVar.a(), a());
        }
    }

    static abstract class e {
        float a;
        float b;
        boolean c;

        protected abstract boolean a(View view, MotionEvent motionEvent);

        e() {
        }
    }

    private static class f {
        int a;
        float b;
        boolean c;

        private f() {
        }
    }

    private class g implements c {
        final float a;
        final float b;
        final e c;
        int d;
        final /* synthetic */ e e;

        g(e eVar, float f, float f2) {
            this.e = eVar;
            this.c = eVar.c();
            this.a = f;
            this.b = f2;
        }

        public int a() {
            return this.d;
        }

        public boolean a(MotionEvent motionEvent) {
            if (this.e.a.a != motionEvent.getPointerId(0)) {
                this.e.a(this.e.e);
            } else {
                View c = this.e.b.c();
                if (this.c.a(c, motionEvent)) {
                    float f = this.c.b / (this.c.c == this.e.a.c ? this.a : this.b);
                    float f2 = this.c.a + f;
                    if ((!this.e.a.c || this.c.c || f2 > this.e.a.b) && (this.e.a.c || !this.c.c || f2 < this.e.a.b)) {
                        if (c.getParent() != null) {
                            c.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
                        if (eventTime > 0) {
                            this.e.i = f / ((float) eventTime);
                        }
                        this.e.a(c, f2);
                        this.e.h.a(this.e, this.d, f2);
                    } else {
                        this.e.a(c, this.e.a.b, motionEvent);
                        this.e.h.a(this.e, this.d, 0.0f);
                        this.e.a(this.e.c);
                    }
                }
            }
            return true;
        }

        public boolean b(MotionEvent motionEvent) {
            this.e.a(this.e.e);
            return false;
        }

        public void a(c cVar) {
            this.d = this.e.a.c ? 1 : 2;
            this.e.g.a(this.e, cVar.a(), a());
        }
    }

    protected abstract void a(View view, float f);

    protected abstract void a(View view, float f, MotionEvent motionEvent);

    protected abstract e c();

    protected abstract a d();

    e(cn.xiaochuankeji.tieba.ui.widget.overscroll.a.a aVar, float f, float f2, float f3) {
        this.b = aVar;
        this.e = new b(this, f);
        this.d = new g(this, f2, f3);
        this.c = new d(this);
        this.f = this.c;
        b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                return this.f.b(motionEvent);
            case 2:
                return this.f.a(motionEvent);
            default:
                return false;
        }
    }

    public void a(c cVar) {
        if (cVar == null) {
            cVar = new cn.xiaochuankeji.tieba.ui.widget.overscroll.d.b();
        }
        this.h = cVar;
    }

    public View a() {
        return this.b.c();
    }

    private void a(c cVar) {
        c cVar2 = this.f;
        this.f = cVar;
        this.f.a(cVar2);
    }

    public void b() {
        a().setOnTouchListener(this);
        a().setOverScrollMode(2);
    }
}
