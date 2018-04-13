package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;

@RequiresApi(14)
class al {
    static final Interpolator a = a.c;
    static final int[] j = new int[]{16842919, 16842910};
    static final int[] k = new int[]{16842908, 16842910};
    static final int[] l = new int[]{16842910};
    static final int[] m = new int[0];
    int b = 0;
    as c;
    Drawable d;
    Drawable e;
    ab f;
    Drawable g;
    float h;
    float i;
    final VisibilityAwareImageButton n;
    final at o;
    private final ax p;
    private float q;
    private final Rect r = new Rect();
    private OnPreDrawListener s;

    interface c {
        void onHidden();

        void onShown();
    }

    private abstract class e extends AnimatorListenerAdapter implements AnimatorUpdateListener {
        private boolean a;
        final /* synthetic */ al b;
        private float c;
        private float d;

        protected abstract float a();

        private e(al alVar) {
            this.b = alVar;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.a) {
                this.c = this.b.c.getShadowSize();
                this.d = a();
                this.a = true;
            }
            this.b.c.setShadowSize(this.c + ((this.d - this.c) * valueAnimator.getAnimatedFraction()));
        }

        public void onAnimationEnd(Animator animator) {
            this.b.c.setShadowSize(this.d);
            this.a = false;
        }
    }

    private class a extends e {
        final /* synthetic */ al a;

        a(al alVar) {
            this.a = alVar;
            super();
        }

        protected float a() {
            return 0.0f;
        }
    }

    private class b extends e {
        final /* synthetic */ al a;

        b(al alVar) {
            this.a = alVar;
            super();
        }

        protected float a() {
            return this.a.h + this.a.i;
        }
    }

    private class d extends e {
        final /* synthetic */ al a;

        d(al alVar) {
            this.a = alVar;
            super();
        }

        protected float a() {
            return this.a.h;
        }
    }

    al(VisibilityAwareImageButton visibilityAwareImageButton, at atVar) {
        this.n = visibilityAwareImageButton;
        this.o = atVar;
        this.p = new ax();
        this.p.addState(j, a(new b(this)));
        this.p.addState(k, a(new b(this)));
        this.p.addState(l, a(new d(this)));
        this.p.addState(m, a(new a(this)));
        this.q = this.n.getRotation();
    }

    void a(ColorStateList colorStateList, Mode mode, int i, int i2) {
        Drawable[] drawableArr;
        this.d = DrawableCompat.wrap(j());
        DrawableCompat.setTintList(this.d, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.d, mode);
        }
        this.e = DrawableCompat.wrap(j());
        DrawableCompat.setTintList(this.e, b(i));
        if (i2 > 0) {
            this.f = a(i2, colorStateList);
            drawableArr = new Drawable[]{this.f, this.d, this.e};
        } else {
            this.f = null;
            drawableArr = new Drawable[]{this.d, this.e};
        }
        this.g = new LayerDrawable(drawableArr);
        this.c = new as(this.n.getContext(), this.g, this.o.getRadius(), this.h, this.h + this.i);
        this.c.setAddPaddingForCorners(false);
        this.o.setBackgroundDrawable(this.c);
    }

    void a(ColorStateList colorStateList) {
        if (this.d != null) {
            DrawableCompat.setTintList(this.d, colorStateList);
        }
        if (this.f != null) {
            this.f.a(colorStateList);
        }
    }

    void a(Mode mode) {
        if (this.d != null) {
            DrawableCompat.setTintMode(this.d, mode);
        }
    }

    void a(int i) {
        if (this.e != null) {
            DrawableCompat.setTintList(this.e, b(i));
        }
    }

    final void a(float f) {
        if (this.h != f) {
            this.h = f;
            a(f, this.i);
        }
    }

    float getElevation() {
        return this.h;
    }

    final void b(float f) {
        if (this.i != f) {
            this.i = f;
            a(this.h, f);
        }
    }

    void a(float f, float f2) {
        if (this.c != null) {
            this.c.a(f, this.i + f);
            d();
        }
    }

    void a(int[] iArr) {
        this.p.a(iArr);
    }

    void a() {
        this.p.jumpToCurrentState();
    }

    void a(@Nullable c cVar, boolean z) {
        if (!m()) {
            this.n.animate().cancel();
            if (o()) {
                this.b = 1;
                this.n.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200).setInterpolator(a.c).setListener(new am(this, z, cVar));
                return;
            }
            this.n.a(z ? 8 : 4, z);
            if (cVar != null) {
                cVar.onHidden();
            }
        }
    }

    void b(@Nullable c cVar, boolean z) {
        if (!l()) {
            this.n.animate().cancel();
            if (o()) {
                this.b = 2;
                if (this.n.getVisibility() != 0) {
                    this.n.setAlpha(0.0f);
                    this.n.setScaleY(0.0f);
                    this.n.setScaleX(0.0f);
                }
                this.n.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200).setInterpolator(a.d).setListener(new an(this, z, cVar));
                return;
            }
            this.n.a(0, z);
            this.n.setAlpha(1.0f);
            this.n.setScaleY(1.0f);
            this.n.setScaleX(1.0f);
            if (cVar != null) {
                cVar.onShown();
            }
        }
    }

    final Drawable b() {
        return this.g;
    }

    void c() {
    }

    final void d() {
        Rect rect = this.r;
        a(rect);
        b(rect);
        this.o.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    void a(Rect rect) {
        this.c.getPadding(rect);
    }

    void b(Rect rect) {
    }

    void e() {
        if (g()) {
            n();
            this.n.getViewTreeObserver().addOnPreDrawListener(this.s);
        }
    }

    void f() {
        if (this.s != null) {
            this.n.getViewTreeObserver().removeOnPreDrawListener(this.s);
            this.s = null;
        }
    }

    boolean g() {
        return true;
    }

    ab a(int i, ColorStateList colorStateList) {
        Context context = this.n.getContext();
        ab h = h();
        h.a(ContextCompat.getColor(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_outer_color));
        h.a((float) i);
        h.a(colorStateList);
        return h;
    }

    ab h() {
        return new ab();
    }

    void i() {
        float rotation = this.n.getRotation();
        if (this.q != rotation) {
            this.q = rotation;
            p();
        }
    }

    private void n() {
        if (this.s == null) {
            this.s = new ao(this);
        }
    }

    GradientDrawable j() {
        GradientDrawable k = k();
        k.setShape(1);
        k.setColor(-1);
        return k;
    }

    GradientDrawable k() {
        return new GradientDrawable();
    }

    boolean l() {
        if (this.n.getVisibility() != 0) {
            if (this.b == 2) {
                return true;
            }
            return false;
        } else if (this.b == 1) {
            return false;
        } else {
            return true;
        }
    }

    boolean m() {
        if (this.n.getVisibility() == 0) {
            if (this.b == 1) {
                return true;
            }
            return false;
        } else if (this.b == 2) {
            return false;
        } else {
            return true;
        }
    }

    private ValueAnimator a(@NonNull e eVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(a);
        valueAnimator.setDuration(100);
        valueAnimator.addListener(eVar);
        valueAnimator.addUpdateListener(eVar);
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        return valueAnimator;
    }

    private static ColorStateList b(int i) {
        r0 = new int[3][];
        int[] iArr = new int[]{k, i, j};
        iArr[1] = i;
        r0[2] = new int[0];
        iArr[2] = 0;
        return new ColorStateList(r0, iArr);
    }

    private boolean o() {
        return ViewCompat.isLaidOut(this.n) && !this.n.isInEditMode();
    }

    private void p() {
        if (VERSION.SDK_INT == 19) {
            if (this.q % 90.0f != 0.0f) {
                if (this.n.getLayerType() != 1) {
                    this.n.setLayerType(1, null);
                }
            } else if (this.n.getLayerType() != 0) {
                this.n.setLayerType(0, null);
            }
        }
        if (this.c != null) {
            this.c.a(-this.q);
        }
        if (this.f != null) {
            this.f.b(-this.q);
        }
    }
}
