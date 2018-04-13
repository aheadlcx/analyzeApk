package com.bdj.picture.edit.a;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;

public class a extends Animation {
    private int a = this.b.getMeasuredHeight();
    private View b;
    private int c = this.d.getMeasuredHeight();
    private View d;
    private View e;
    private int f;
    private LayoutParams g;
    private LayoutParams h;
    private ViewGroup.LayoutParams i;
    private int j;

    public a(View view, View view2, View view3, int i) {
        this.b = view;
        this.d = view2;
        this.e = view3;
        this.g = (LayoutParams) view.getLayoutParams();
        this.h = (LayoutParams) view2.getLayoutParams();
        this.i = this.e.getLayoutParams();
        this.j = this.e.getMeasuredHeight();
        this.f = i;
        if (this.f == 0) {
            this.g.topMargin = -this.a;
            this.h.bottomMargin = -this.c;
        } else {
            this.g.topMargin = 0;
            this.h.bottomMargin = 0;
        }
        view.setVisibility(0);
        view2.setVisibility(0);
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        setDuration(500);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        if (f < 1.0f) {
            if (this.f == 0) {
                this.g.topMargin = (-this.a) + ((int) (((float) this.a) * f));
                this.h.bottomMargin = (-this.c) + ((int) (((float) this.c) * f));
                this.i.height = (this.j - ((int) (((float) this.a) * f))) - ((int) (((float) this.c) * f));
            } else {
                this.g.topMargin = -((int) (((float) this.a) * f));
                this.h.bottomMargin = -((int) (((float) this.c) * f));
                this.i.height = (this.j - this.g.topMargin) - this.h.bottomMargin;
            }
            Log.i("tangjian", "topMargin: " + this.g.topMargin + "  bottomMargin: " + this.h.bottomMargin + "  height: " + this.i.height);
            this.e.requestLayout();
            this.b.requestLayout();
            this.d.requestLayout();
            return;
        }
        if (this.f == 0) {
            this.g.topMargin = 0;
            this.h.bottomMargin = 0;
            this.i.height = (this.j - this.a) - this.c;
        } else {
            this.g.topMargin = -this.a;
            this.h.bottomMargin = -this.c;
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.i.height = (this.j + this.a) + this.c;
        }
        this.b.requestLayout();
        this.d.requestLayout();
        this.e.requestLayout();
    }
}
