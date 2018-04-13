package com.budejie.www.activity.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout.LayoutParams;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JazzyViewPager extends ViewPager {
    public static int a = -1;
    private static final boolean g = (VERSION.SDK_INT >= 11);
    private boolean b;
    private boolean c;
    private boolean d;
    private TransitionEffect e;
    private HashMap<Integer, Object> f;
    private b h;
    private int i;
    private View j;
    private View k;
    private float l;
    private float m;
    private float n;
    private Matrix o;
    private Camera p;
    private float[] q;
    private b r;
    private a s;

    public interface a {
        void a();
    }

    public enum TransitionEffect {
        Standard,
        Tablet,
        CubeIn,
        CubeOut,
        FlipVertical,
        FlipHorizontal,
        Stack,
        ZoomIn,
        ZoomOut,
        RotateUp,
        RotateDown,
        Accordion
    }

    private enum b {
        IDLE,
        GOING_LEFT,
        GOING_RIGHT
    }

    public JazzyViewPager(Context context) {
        this(context, null);
        c();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public JazzyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = true;
        this.c = false;
        this.d = false;
        this.e = TransitionEffect.Standard;
        this.f = new LinkedHashMap();
        this.o = new Matrix();
        this.p = new Camera();
        this.q = new float[2];
        this.r = null;
        c();
        setClipChildren(false);
        switch (this.e) {
            case Stack:
            case ZoomOut:
                setFadeEnabled(true);
                return;
            default:
                return;
        }
    }

    public void setTransitionEffect(TransitionEffect transitionEffect) {
        this.e = transitionEffect;
    }

    public void setPagingEnabled(boolean z) {
        this.b = z;
    }

    public void setFadeEnabled(boolean z) {
        this.c = z;
    }

    public boolean getFadeEnabled() {
        return this.c;
    }

    public void setOutlineEnabled(boolean z) {
        this.d = z;
        a();
    }

    public void setOutlineColor(int i) {
        a = i;
    }

    private void a() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof OutlineContainer)) {
                removeView(childAt);
                super.addView(a(childAt), i);
            }
        }
    }

    private View a(View view) {
        if (!this.d || (view instanceof OutlineContainer)) {
            return view;
        }
        View outlineContainer = new OutlineContainer(getContext());
        outlineContainer.setLayoutParams(generateDefaultLayoutParams());
        view.setLayoutParams(new LayoutParams(-1, -1));
        outlineContainer.addView(view);
        return outlineContainer;
    }

    public void addView(View view) {
        super.addView(a(view));
    }

    public void addView(View view, int i) {
        super.addView(a(view), i);
    }

    public void addView(View view, int i, int i2) {
        super.addView(a(view), i, i2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    private void a(View view, String str) {
        Log.v("JazzyViewPager", str + ": ROT (" + com.nineoldandroids.b.a.b(view) + ", " + com.nineoldandroids.b.a.c(view) + ", " + com.nineoldandroids.b.a.d(view) + "), TRANS (" + com.nineoldandroids.b.a.g(view) + ", " + com.nineoldandroids.b.a.h(view) + "), SCALE (" + com.nineoldandroids.b.a.e(view) + ", " + com.nineoldandroids.b.a.f(view) + "), ALPHA " + com.nineoldandroids.b.a.a(view));
    }

    protected void a(View view, View view2, float f) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.l = 30.0f * f;
                this.m = a(this.l, view.getMeasuredWidth(), view.getMeasuredHeight());
                com.nineoldandroids.b.a.b(view, (float) (view.getMeasuredWidth() / 2));
                com.nineoldandroids.b.a.c(view, (float) (view.getMeasuredHeight() / 2));
                com.nineoldandroids.b.a.i(view, this.m);
                com.nineoldandroids.b.a.f(view, this.l);
                a(view, "Left");
            }
            if (view2 != null) {
                a(view2, true);
                this.l = -30.0f * (1.0f - f);
                this.m = a(this.l, view2.getMeasuredWidth(), view2.getMeasuredHeight());
                com.nineoldandroids.b.a.b(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.i(view2, this.m);
                com.nineoldandroids.b.a.f(view2, this.l);
                a(view2, "Right");
            }
        }
    }

    private void a(View view, View view2, float f, boolean z) {
        float f2 = 90.0f;
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.l = (z ? 90.0f : -90.0f) * f;
                com.nineoldandroids.b.a.b(view, (float) view.getMeasuredWidth());
                com.nineoldandroids.b.a.c(view, ((float) view.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.f(view, this.l);
            }
            if (view2 != null) {
                a(view2, true);
                if (!z) {
                    f2 = -90.0f;
                }
                this.l = (-f2) * (1.0f - f);
                com.nineoldandroids.b.a.b(view2, 0.0f);
                com.nineoldandroids.b.a.c(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.f(view2, this.l);
            }
        }
    }

    private void c(View view, View view2, float f) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                com.nineoldandroids.b.a.b(view, (float) view.getMeasuredWidth());
                com.nineoldandroids.b.a.c(view, 0.0f);
                com.nineoldandroids.b.a.g(view, 1.0f - f);
            }
            if (view2 != null) {
                a(view2, true);
                com.nineoldandroids.b.a.b(view2, 0.0f);
                com.nineoldandroids.b.a.c(view2, 0.0f);
                com.nineoldandroids.b.a.g(view2, f);
            }
        }
    }

    private void b(View view, View view2, float f, boolean z) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.n = z ? ((1.0f - f) * 0.5f) + 0.5f : 1.5f - ((1.0f - f) * 0.5f);
                com.nineoldandroids.b.a.b(view, ((float) view.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view, ((float) view.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.g(view, this.n);
                com.nineoldandroids.b.a.h(view, this.n);
            }
            if (view2 != null) {
                a(view2, true);
                this.n = z ? (0.5f * f) + 0.5f : 1.5f - (0.5f * f);
                com.nineoldandroids.b.a.b(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.g(view2, this.n);
                com.nineoldandroids.b.a.h(view2, this.n);
            }
        }
    }

    private void c(View view, View view2, float f, boolean z) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.l = ((float) (z ? 1 : -1)) * (15.0f * f);
                this.m = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.l) * 3.141592653589793d) / 180.0d))));
                com.nineoldandroids.b.a.b(view, ((float) view.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view, z ? 0.0f : (float) view.getMeasuredHeight());
                com.nineoldandroids.b.a.j(view, this.m);
                com.nineoldandroids.b.a.d(view, this.l);
            }
            if (view2 != null) {
                a(view2, true);
                this.l = ((float) (z ? 1 : -1)) * (-15.0f + (15.0f * f));
                this.m = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.l) * 3.141592653589793d) / 180.0d))));
                com.nineoldandroids.b.a.b(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view2, z ? 0.0f : (float) view2.getMeasuredHeight());
                com.nineoldandroids.b.a.j(view2, this.m);
                com.nineoldandroids.b.a.d(view2, this.l);
            }
        }
    }

    private void b(View view, View view2, float f, int i) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.l = 180.0f * f;
                if (this.l > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.m = (float) i;
                    com.nineoldandroids.b.a.b(view, ((float) view.getMeasuredWidth()) * 0.5f);
                    com.nineoldandroids.b.a.c(view, ((float) view.getMeasuredHeight()) * 0.5f);
                    com.nineoldandroids.b.a.i(view, this.m);
                    com.nineoldandroids.b.a.f(view, this.l);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.l = -180.0f * (1.0f - f);
                if (this.l < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.m = (float) (((-getWidth()) - getPageMargin()) + i);
                com.nineoldandroids.b.a.b(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.i(view2, this.m);
                com.nineoldandroids.b.a.f(view2, this.l);
            }
        }
    }

    private void c(View view, View view2, float f, int i) {
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                this.l = 180.0f * f;
                if (this.l > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.m = (float) i;
                    com.nineoldandroids.b.a.b(view, ((float) view.getMeasuredWidth()) * 0.5f);
                    com.nineoldandroids.b.a.c(view, ((float) view.getMeasuredHeight()) * 0.5f);
                    com.nineoldandroids.b.a.i(view, this.m);
                    com.nineoldandroids.b.a.e(view, this.l);
                }
            }
            if (view2 != null) {
                a(view2, true);
                this.l = -180.0f * (1.0f - f);
                if (this.l < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.m = (float) (((-getWidth()) - getPageMargin()) + i);
                com.nineoldandroids.b.a.b(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                com.nineoldandroids.b.a.c(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                com.nineoldandroids.b.a.i(view2, this.m);
                com.nineoldandroids.b.a.e(view2, this.l);
            }
        }
    }

    protected void a(View view, View view2, float f, int i) {
        if (this.h != b.IDLE) {
            if (view2 != null) {
                a(view2, true);
                this.n = (0.5f * f) + 0.5f;
                this.m = (float) (((-getWidth()) - getPageMargin()) + i);
                com.nineoldandroids.b.a.g(view2, this.n);
                com.nineoldandroids.b.a.h(view2, this.n);
                com.nineoldandroids.b.a.i(view2, this.m);
            }
            if (view != null) {
                view.bringToFront();
            }
        }
    }

    @TargetApi(11)
    private void a(View view, boolean z) {
        if (g) {
            int i = z ? 2 : 0;
            if (i != view.getLayerType()) {
                view.setLayerType(i, null);
            }
        }
    }

    @TargetApi(11)
    private void b() {
        if (g) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getLayerType() != 0) {
                    childAt.setLayerType(0, null);
                }
            }
        }
    }

    protected float a(float f, int i, int i2) {
        this.o.reset();
        this.p.save();
        this.p.rotateY(Math.abs(f));
        this.p.getMatrix(this.o);
        this.p.restore();
        this.o.preTranslate(((float) (-i)) * 0.5f, ((float) (-i2)) * 0.5f);
        this.o.postTranslate(((float) i) * 0.5f, ((float) i2) * 0.5f);
        this.q[0] = (float) i;
        this.q[1] = (float) i2;
        this.o.mapPoints(this.q);
        return (f > 0.0f ? 1.0f : -1.0f) * (((float) i) - this.q[0]);
    }

    protected void b(View view, View view2, float f) {
        if (view != null) {
            com.nineoldandroids.b.a.a(view, 1.0f - f);
        }
        if (view2 != null) {
            com.nineoldandroids.b.a.a(view2, f);
        }
    }

    protected void a(View view, View view2) {
        if (!(view instanceof OutlineContainer)) {
            return;
        }
        if (this.h != b.IDLE) {
            if (view != null) {
                a(view, true);
                ((OutlineContainer) view).setOutlineAlpha(1.0f);
            }
            if (view2 != null) {
                a(view2, true);
                ((OutlineContainer) view2).setOutlineAlpha(1.0f);
                return;
            }
            return;
        }
        if (view != null) {
            ((OutlineContainer) view).start();
        }
        if (view2 != null) {
            ((OutlineContainer) view2).start();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        float f2;
        if (this.h == b.IDLE && f > 0.0f) {
            this.i = getCurrentItem();
            this.h = i == this.i ? b.GOING_RIGHT : b.GOING_LEFT;
        }
        boolean z;
        if (i == this.i) {
            z = true;
        } else {
            z = false;
        }
        if (this.h == b.GOING_RIGHT && !r0) {
            this.h = b.GOING_LEFT;
        } else if (this.h == b.GOING_LEFT && r0) {
            this.h = b.GOING_RIGHT;
        }
        if (a(f)) {
            f2 = 0.0f;
        } else {
            f2 = f;
        }
        this.j = a(i);
        this.k = a(i + 1);
        if (this.c) {
            b(this.j, this.k, f2);
        }
        if (this.d) {
            a(this.j, this.k);
        }
        switch (this.e) {
            case Stack:
                break;
            case ZoomOut:
                b(this.j, this.k, f2, false);
                break;
            case Tablet:
                a(this.j, this.k, f2);
                break;
            case CubeIn:
                a(this.j, this.k, f2, true);
                break;
            case CubeOut:
                a(this.j, this.k, f2, false);
                break;
            case FlipVertical:
                c(this.j, this.k, f, i2);
                break;
            case FlipHorizontal:
                b(this.j, this.k, f2, i2);
                break;
            case ZoomIn:
                b(this.j, this.k, f2, true);
                break;
            case RotateUp:
                c(this.j, this.k, f2, true);
                break;
            case RotateDown:
                c(this.j, this.k, f2, false);
                break;
            case Accordion:
                c(this.j, this.k, f2);
                break;
        }
        a(this.j, this.k, f2, i2);
        super.onPageScrolled(i, f, i2);
        if (f2 == 0.0f) {
            b();
            this.h = b.IDLE;
        }
    }

    private boolean a(float f) {
        return ((double) Math.abs(f)) < 1.0E-4d;
    }

    public void a(Object obj, int i) {
        this.f.put(Integer.valueOf(i), obj);
    }

    public View a(int i) {
        Object obj = this.f.get(Integer.valueOf(i));
        if (obj == null) {
            return null;
        }
        PagerAdapter adapter = getAdapter();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (adapter.isViewFromObject(childAt, obj)) {
                return childAt;
            }
        }
        return null;
    }

    private void c() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.r = new b(getContext(), (Interpolator) declaredField2.get(null));
            declaredField.set(this, this.r);
        } catch (Exception e) {
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.s != null && !this.r.computeScrollOffset()) {
            this.s.a();
        }
    }

    public void setScrollDurationFactor(double d) {
        this.r.a(d);
    }

    public void setOnScrollExecutOverListener(a aVar) {
        this.s = aVar;
    }
}
