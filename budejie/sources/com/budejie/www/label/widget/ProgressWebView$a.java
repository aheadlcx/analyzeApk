package com.budejie.www.label.widget;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.util.an;

class ProgressWebView$a extends Animation {
    final /* synthetic */ ProgressWebView a;

    private ProgressWebView$a(ProgressWebView progressWebView) {
        this.a = progressWebView;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        setDuration(an.c(BudejieApplication.g) ? 6000 : 4000);
        setInterpolator(new DecelerateInterpolator());
    }

    protected void applyTransformation(float f, Transformation transformation) {
        int f2 = (int) (((float) ProgressWebView.f(this.a)) * f);
        if (f2 > ProgressWebView.a(this.a).getProgress()) {
            ProgressWebView.a(this.a).setProgress(f2);
        }
    }
}
