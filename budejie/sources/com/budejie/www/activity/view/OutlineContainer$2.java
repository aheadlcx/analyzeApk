package com.budejie.www.activity.view;

import android.view.animation.AnimationUtils;

class OutlineContainer$2 implements Runnable {
    final /* synthetic */ OutlineContainer a;

    OutlineContainer$2(OutlineContainer outlineContainer) {
        this.a = outlineContainer;
    }

    public void run() {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - OutlineContainer.a(this.a);
        if (currentAnimationTimeMillis >= 500) {
            OutlineContainer.a(this.a, 0.0f);
            this.a.invalidate();
            this.a.stop();
            return;
        }
        OutlineContainer.a(this.a, OutlineContainer.b(this.a).getInterpolation(1.0f - (((float) currentAnimationTimeMillis) / 500.0f)));
        this.a.invalidate();
        this.a.postDelayed(OutlineContainer.c(this.a), 16);
    }
}
