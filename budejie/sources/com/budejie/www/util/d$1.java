package com.budejie.www.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.budejie.www.activity.view.BadgeView;

class d$1 implements AnimationListener {
    final /* synthetic */ BadgeView a;
    final /* synthetic */ View b;

    d$1(BadgeView badgeView, View view) {
        this.a = badgeView;
        this.b = view;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        try {
            ViewParent parent = this.a.getParent();
            View view = (ViewGroup) parent;
            view.removeView(this.b);
            LayoutParams layoutParams = view.getLayoutParams();
            ViewGroup viewGroup = (ViewGroup) parent.getParent();
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            viewGroup.addView(this.b, indexOfChild, layoutParams);
            viewGroup.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
