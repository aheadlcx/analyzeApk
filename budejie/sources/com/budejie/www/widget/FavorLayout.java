package com.budejie.www.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import java.util.Random;

public class FavorLayout extends RelativeLayout {
    private Interpolator a = new LinearInterpolator();
    private Interpolator b = new AccelerateInterpolator();
    private Interpolator c = new DecelerateInterpolator();
    private Interpolator d = new AccelerateDecelerateInterpolator();
    private Interpolator[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Drawable j;
    private Drawable k;
    private Drawable l;
    private Drawable[] m;
    private Random n = new Random();
    private int o;
    private int p;

    public FavorLayout(Context context) {
        super(context);
    }

    public FavorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.m = new Drawable[3];
        this.j = getResources().getDrawable(R.drawable.barrage_praise_ing);
        this.k = getResources().getDrawable(R.drawable.barrage_praise_ing);
        this.l = getResources().getDrawable(R.drawable.barrage_praise_ing);
        this.m[0] = this.j;
        this.m[1] = this.k;
        this.m[2] = this.l;
        this.o = this.j.getIntrinsicHeight();
        this.p = this.j.getIntrinsicWidth();
        this.e = new Interpolator[4];
        this.e[0] = this.a;
        this.e[1] = this.b;
        this.e[2] = this.c;
        this.e[3] = this.d;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.g = getMeasuredWidth();
        this.f = getMeasuredHeight();
    }

    @SuppressLint({"NewApi"})
    public void a(float f, float f2) {
        Log.d("FavorLayout", "x=" + f + " y=" + f2);
        this.h = ((int) f) - 100;
        this.i = (int) f2;
        for (int i = 0; i < 5; i++) {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.p, this.o);
            layoutParams.topMargin = this.i - this.o;
            layoutParams.leftMargin = this.h;
            View imageView = new ImageView(getContext());
            imageView.setImageDrawable(this.m[this.n.nextInt(3)]);
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
            Log.v("FavorLayout", "add后子view数:" + getChildCount());
            Animator a = a(imageView);
            a.addListener(new FavorLayout$a(this, imageView));
            a.start();
        }
    }

    @SuppressLint({"NewApi"})
    private Animator a(View view) {
        AnimatorSet b = b(view);
        ValueAnimator c = c(view);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{b});
        animatorSet.playSequentially(new Animator[]{b, c});
        animatorSet.setInterpolator(this.e[this.n.nextInt(4)]);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    @SuppressLint({"NewApi"})
    private AnimatorSet b(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.2f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.2f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.2f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    @SuppressLint({"NewApi"})
    private ValueAnimator c(View view) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new c(a(2), a(1)), new Object[]{new PointF((float) this.h, (float) (this.i - this.o)), new PointF((float) ((this.h + this.n.nextInt(300)) - 150), (float) (this.i - 300))});
        ofObject.addUpdateListener(new FavorLayout$b(this, view));
        ofObject.setTarget(view);
        ofObject.setDuration(2500);
        return ofObject;
    }

    private PointF a(int i) {
        PointF pointF = new PointF();
        pointF.x = (float) ((this.h + this.n.nextInt(250)) - 125);
        pointF.y = (float) (this.i - (this.n.nextInt(300) / i));
        return pointF;
    }
}
