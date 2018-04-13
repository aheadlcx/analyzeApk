package com.c.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.c.a.a.b;
import java.util.ArrayList;
import java.util.List;

public class d extends FrameLayout {
    Drawable a;
    private float b;
    private Activity c;
    private boolean d;
    private boolean e;
    private View f;
    private h g;
    private float h;
    private int i;
    private List<f> j;
    private float k;
    private int l;
    private boolean m;
    private Rect n;
    private int o;
    private boolean p;

    public void setPageTranslucent(boolean z) {
        this.p = z;
    }

    public boolean a() {
        return this.p;
    }

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0.3f;
        this.d = true;
        this.e = false;
        this.l = -1728053248;
        this.n = new Rect();
        this.p = true;
        this.g = h.a(this, new d$a(this, null));
        setShadow(b.swipe_shadow);
        float f = getResources().getDisplayMetrics().density * 400.0f;
        setEdgeSize(getResources().getDisplayMetrics().widthPixels);
        this.g.a(f);
        this.g.b(f * 2.0f);
        this.g.a(context, 0.3f);
        this.g.a(1);
    }

    public void a(Context context, float f) {
        this.g.a(context, f);
    }

    private void setContentView(View view) {
        this.f = view;
    }

    public void setEnableGesture(boolean z) {
        this.d = z;
    }

    public void setScrimColor(int i) {
        this.l = i;
        invalidate();
    }

    public void setEdgeSize(int i) {
        this.o = i;
        this.g.b(this.o);
    }

    public void setEdgeSizePercent(float f) {
        this.o = (int) (((float) getResources().getDisplayMetrics().widthPixels) * f);
        this.g.b(this.o);
    }

    @Deprecated
    public void setSwipeListener(f fVar) {
        a(fVar);
    }

    public void a(f fVar) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        this.j.add(fVar);
    }

    public void b(f fVar) {
        if (this.j != null) {
            this.j.remove(fVar);
        }
    }

    public void setScrollThreshold(float f) {
        if (f >= 1.0f || f <= 0.0f) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        this.b = f;
    }

    public void setShadow(Drawable drawable) {
        this.a = drawable;
        invalidate();
    }

    public void setShadow(int i) {
        setShadow(getResources().getDrawable(i));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.d && !this.e) {
            try {
                z = this.g.a(motionEvent);
            } catch (Exception e) {
            }
        }
        return z;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.d) {
            return false;
        }
        try {
            this.g.b(motionEvent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setDisallowInterceptTouchEvent(boolean z) {
        this.e = z;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.m = true;
        if (this.f != null) {
            this.f.layout(this.i, 0, this.i + this.f.getMeasuredWidth(), this.f.getMeasuredHeight());
        }
        this.m = false;
    }

    public void requestLayout() {
        if (!this.m) {
            super.requestLayout();
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        Object obj = view == this.f ? 1 : null;
        boolean drawChild = super.drawChild(canvas, view, j);
        if (!(this.k <= 0.0f || obj == null || this.g.a() == 0)) {
            b(canvas, view);
            a(canvas, view);
        }
        return drawChild;
    }

    private void a(Canvas canvas, View view) {
        int i = (((int) (((float) ((this.l & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.k)) << 24) | (this.l & ViewCompat.MEASURED_SIZE_MASK);
        canvas.clipRect(0, 0, view.getLeft(), getHeight());
        canvas.drawColor(i);
    }

    private void b(Canvas canvas, View view) {
        Rect rect = this.n;
        view.getHitRect(rect);
        this.a.setBounds(rect.left - this.a.getIntrinsicWidth(), rect.top, rect.left, rect.bottom);
        this.a.setAlpha((int) (this.k * 255.0f));
        this.a.draw(canvas);
    }

    public void a(Activity activity) {
        if (getParent() == null) {
            this.c = activity;
            TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            obtainStyledAttributes.recycle();
            ViewParent viewParent = (ViewGroup) activity.getWindow().getDecorView();
            View findViewById = viewParent.findViewById(16908290);
            while (findViewById.getParent() != viewParent) {
                findViewById = (View) findViewById.getParent();
            }
            findViewById.setBackgroundResource(resourceId);
            viewParent.removeView(findViewById);
            addView(findViewById);
            setContentView(findViewById);
            viewParent.addView(this);
        }
    }

    public void b(Activity activity) {
        if (getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            ViewGroup viewGroup2 = (ViewGroup) activity.getWindow().getDecorView();
            viewGroup2.removeView(this);
            removeView(viewGroup);
            viewGroup2.addView(viewGroup);
        }
    }

    public void computeScroll() {
        this.k = 1.0f - this.h;
        if (this.g.a(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
