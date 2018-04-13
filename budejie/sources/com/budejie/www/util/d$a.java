package com.budejie.www.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class d$a implements AnimationListener {
    Context a;
    TextView b;

    public d$a(Context context, TextView textView) {
        this.a = context;
        this.b = textView;
    }

    public void onAnimationEnd(Animation animation) {
        d.a(this.a, this.b);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
