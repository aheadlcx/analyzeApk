package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.budejie.www.R;
import com.nineoldandroids.a.a.a;
import com.nineoldandroids.a.g;

public class CarouselContainer extends LinearLayout {
    private float[] a = new float[2];
    private final int b = getResources().getDimensionPixelSize(R.dimen.titlebar_height);
    private boolean c;
    private int d = Integer.MIN_VALUE;
    private int e = Integer.MIN_VALUE;
    private int f;
    private final a g = new a(this) {
        final /* synthetic */ CarouselContainer a;

        {
            this.a = r1;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            this.a.c = false;
        }

        public void b(com.nineoldandroids.a.a aVar) {
            this.a.c = true;
        }

        public void c(com.nineoldandroids.a.a aVar) {
            this.a.c = true;
        }
    };

    public enum TextViewType {
        DOWNLOAD,
        PAGES,
        DATE,
        SIZE
    }

    public CarouselContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.e != i) {
            this.e = i;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean a() {
        return this.c;
    }

    public void a(int i, float f) {
        this.a[i] = ((float) this.b) + f;
    }

    private void b(int i) {
        Interpolator loadInterpolator = AnimationUtils.loadInterpolator(getContext(), 17432580);
        g a = g.a(this, "y", a(i));
        a.a(this.g);
        a.a(loadInterpolator);
        a.a(0);
        a.a();
    }

    public void b(int i, float f) {
        if (i == this.f) {
            a(i, f);
            b(i);
        }
    }

    public void setCurrentTab(int i) {
        this.f = i;
    }

    public float a(int i) {
        return this.a[i];
    }

    public int getAllowedVerticalScrollLength() {
        return this.d;
    }
}
