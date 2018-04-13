package com.flyco.a.b;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class a extends com.flyco.a.a {
    public void a(View view) {
        this.b.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f}).setDuration(this.a)});
    }
}
