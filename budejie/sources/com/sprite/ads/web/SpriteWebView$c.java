package com.sprite.ads.web;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;

class SpriteWebView$c extends Animation {
    final /* synthetic */ SpriteWebView a;
    private int b;
    private int c;

    public SpriteWebView$c(SpriteWebView spriteWebView, int i, int i2) {
        this.a = spriteWebView;
        this.b = i;
        this.c = i2;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        int i = (int) (((float) this.b) + (((float) (this.c - this.b)) * f));
        if (i > SpriteWebView.a(this.a).getProgress()) {
            SpriteWebView.a(this.a).setProgress(i);
        }
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        int b = ((this.c - this.b) * ErrorCode.SERVER_SESSIONSTATUS) / SpriteWebView.b(this.a);
        long j = (this.c != SpriteWebView.b(this.a) || b <= 200) ? (long) b : 200;
        setDuration(j);
        setInterpolator(new DecelerateInterpolator());
    }
}
