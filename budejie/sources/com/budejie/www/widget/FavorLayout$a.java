package com.budejie.www.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

@SuppressLint({"NewApi"})
class FavorLayout$a extends AnimatorListenerAdapter {
    final /* synthetic */ FavorLayout a;
    private View b;

    public FavorLayout$a(FavorLayout favorLayout, View view) {
        this.a = favorLayout;
        this.b = view;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.removeView(this.b);
        Log.v("FavorLayout", "removeView后子view数:" + this.a.getChildCount());
    }
}
