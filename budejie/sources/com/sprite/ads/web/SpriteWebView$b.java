package com.sprite.ads.web;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

class SpriteWebView$b extends Animation {
    final /* synthetic */ SpriteWebView a;

    private SpriteWebView$b(SpriteWebView spriteWebView) {
        this.a = spriteWebView;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        int e = (int) (((float) SpriteWebView.e(this.a)) * f);
        if (e > SpriteWebView.a(this.a).getProgress()) {
            SpriteWebView.a(this.a).setProgress(e);
        }
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        setInterpolator(new DecelerateInterpolator());
    }
}
