package com.budejie.www.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.view.View;

@SuppressLint({"NewApi"})
class FavorLayout$b implements AnimatorUpdateListener {
    final /* synthetic */ FavorLayout a;
    private View b;

    public FavorLayout$b(FavorLayout favorLayout, View view) {
        this.a = favorLayout;
        this.b = view;
    }

    @SuppressLint({"NewApi"})
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        PointF pointF = (PointF) valueAnimator.getAnimatedValue();
        this.b.setX(pointF.x);
        this.b.setY(pointF.y);
        this.b.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
    }
}
