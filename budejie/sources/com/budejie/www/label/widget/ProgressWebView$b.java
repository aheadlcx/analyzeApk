package com.budejie.www.label.widget;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;

class ProgressWebView$b extends Animation {
    final /* synthetic */ ProgressWebView a;
    private int b;
    private int c;

    public ProgressWebView$b(ProgressWebView progressWebView, int i, int i2) {
        this.a = progressWebView;
        this.b = i;
        this.c = i2;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        int b = ((this.c - this.b) * ErrorCode.SERVER_SESSIONSTATUS) / ProgressWebView.b(this.a);
        long j = (this.c != ProgressWebView.b(this.a) || b <= 200) ? (long) b : 200;
        setDuration(j);
        setInterpolator(new DecelerateInterpolator());
    }

    protected void applyTransformation(float f, Transformation transformation) {
        int i = (int) (((float) this.b) + (((float) (this.c - this.b)) * f));
        if (i > ProgressWebView.a(this.a).getProgress()) {
            ProgressWebView.a(this.a).setProgress(i);
        }
    }
}
