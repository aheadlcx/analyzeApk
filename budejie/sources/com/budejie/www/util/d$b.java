package com.budejie.www.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class d$b implements AnimationListener {
    TextView a;

    public d$b(TextView textView) {
        this.a = textView;
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
