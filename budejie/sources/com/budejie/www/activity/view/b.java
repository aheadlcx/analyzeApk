package com.budejie.www.activity.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class b extends Scroller {
    private double a = 1.0d;

    public b(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void a(double d) {
        this.a = d;
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, (int) (((double) i5) * this.a));
    }
}
