package com.scwang.smartrefresh.layout.b;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.scwang.smartrefresh.layout.a.e;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.b.a.b;
import com.scwang.smartrefresh.layout.b.a.c;
import com.scwang.smartrefresh.layout.b.a.d;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class a extends FrameLayout implements e {
    private d a;
    private com.scwang.smartrefresh.layout.b.a.a b;
    private b c;
    private c d;
    private boolean e;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        setMinimumHeight(com.scwang.smartrefresh.layout.f.b.a(100.0f));
        this.a = new d(getContext());
        this.b = new com.scwang.smartrefresh.layout.b.a.a(getContext());
        this.c = new b(getContext());
        this.d = new c(getContext());
        if (isInEditMode()) {
            addView(this.a, -1, -1);
            addView(this.d, -1, -1);
            this.a.setHeadHeight(1000);
        } else {
            addView(this.a, -1, -1);
            addView(this.c, -1, -1);
            addView(this.d, -1, -1);
            addView(this.b, -1, -1);
            this.d.setScaleX(0.0f);
            this.d.setScaleY(0.0f);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.scwang.smartrefresh.layout.a.a.BezierRadarHeader);
        this.e = obtainStyledAttributes.getBoolean(com.scwang.smartrefresh.layout.a.a.BezierRadarHeader_srlEnableHorizontalDrag, this.e);
        int color = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.BezierRadarHeader_srlPrimaryColor, 0);
        int color2 = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.BezierRadarHeader_srlAccentColor, 0);
        if (color != 0) {
            a(color);
        }
        if (color2 != 0) {
            b(color2);
        }
        obtainStyledAttributes.recycle();
    }

    public a a(@ColorInt int i) {
        this.a.setWaveColor(i);
        this.d.setBackColor(i);
        return this;
    }

    public a b(@ColorInt int i) {
        this.c.setDotColor(i);
        this.b.setFrontColor(i);
        this.d.setFrontColor(i);
        return this;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            a(iArr[0]);
        }
        if (iArr.length > 1) {
            b(iArr[1]);
        }
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Scale;
    }

    public void a(@NonNull g gVar, int i, int i2) {
    }

    public boolean a() {
        return this.e;
    }

    public void a(float f, int i, int i2) {
        this.a.setWaveOffsetX(i);
        this.a.invalidate();
    }

    public void a(float f, int i, int i2, int i3) {
        this.a.setHeadHeight(Math.min(i2, i));
        this.a.setWaveHeight((int) (1.9f * ((float) Math.max(0, i - i2))));
        this.c.setFraction(f);
    }

    public void b(float f, int i, int i2, int i3) {
        a(f, i, i2, i3);
    }

    public void a(h hVar, int i, int i2) {
        this.a.setHeadHeight(i);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.a.getWaveHeight(), 0, -((int) (((double) this.a.getWaveHeight()) * 0.8d)), 0, -((int) (((float) this.a.getWaveHeight()) * 0.4f)), 0});
        ofInt.addUpdateListener(new a$1(this));
        ofInt.setInterpolator(new DecelerateInterpolator());
        ofInt.setDuration(800);
        ofInt.start();
        ofInt = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofInt.addListener(new a$2(this, hVar));
        ofInt.setInterpolator(new DecelerateInterpolator());
        ofInt.setDuration(300);
        ofInt.addUpdateListener(new a$3(this));
        ofInt.start();
    }

    public void b(@NonNull h hVar, int i, int i2) {
    }

    public int a(@NonNull h hVar, boolean z) {
        this.d.b();
        this.d.animate().scaleX(0.0f);
        this.d.animate().scaleY(0.0f);
        this.b.setVisibility(0);
        this.b.a();
        return 400;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        switch (a$4.a[refreshState2.ordinal()]) {
            case 1:
                this.b.setVisibility(8);
                this.c.setAlpha(1.0f);
                this.c.setVisibility(0);
                return;
            case 2:
                this.d.setScaleX(0.0f);
                this.d.setScaleY(0.0f);
                return;
            default:
                return;
        }
    }
}
