package com.budejie.www.widget.curtain;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout.LayoutParams;

public class a extends Animation {
    private int a = 300;
    private a b;
    private View c;
    private LayoutParams d;
    private int e;

    enum a {
        COLLAPSE,
        EXPAND
    }

    public static a a(View view) {
        return new a(view, a.EXPAND, 300);
    }

    public static a a(View view, int i) {
        return new a(view, a.COLLAPSE, i);
    }

    public static a b(View view) {
        return new a(view, a.COLLAPSE, 300);
    }

    private a(View view, a aVar, int i) {
        this.c = view;
        this.b = aVar;
        this.a = i;
        this.d = (LayoutParams) this.c.getLayoutParams();
        this.e = aVar == a.EXPAND ? this.d.leftMargin : view.getWidth();
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        setDuration((long) this.a);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @SuppressLint({"NewApi"})
    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        if (f < 1.0f) {
            if (this.b == a.EXPAND) {
                this.d.leftMargin = (int) ((1.0f - f) * ((float) this.e));
            } else {
                this.d.leftMargin = (int) (-(((float) this.e) * f));
            }
        } else if (this.b == a.EXPAND) {
            this.d.leftMargin = 0;
        } else {
            this.d.leftMargin = -this.e;
        }
        Log.i("tangjian", "" + this.d.leftMargin);
        this.c.requestLayout();
    }
}
