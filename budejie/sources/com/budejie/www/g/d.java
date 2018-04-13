package com.budejie.www.g;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class d implements AnimationListener {
    View a;
    Handler b;
    int c;

    public d(View view, Handler handler, int i) {
        this.a = view;
        this.b = handler;
        this.c = i;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a != null) {
            this.a.setVisibility(4);
        }
        this.b.sendEmptyMessage(this.c);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        if (this.a != null) {
            this.a.setVisibility(0);
        }
    }
}
