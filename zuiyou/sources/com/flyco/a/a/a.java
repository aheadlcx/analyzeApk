package com.flyco.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

public class a extends com.flyco.a.a {
    public a() {
        this.a = 1000;
    }

    public void a(View view) {
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        AnimatorSet animatorSet = this.b;
        Animator[] animatorArr = new Animator[2];
        animatorArr[0] = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f, 1.0f, 1.0f});
        animatorArr[1] = ObjectAnimator.ofFloat(view, "translationX", new float[]{displayMetrics.density * -250.0f, 30.0f, -10.0f, 0.0f});
        animatorSet.playTogether(animatorArr);
    }
}
