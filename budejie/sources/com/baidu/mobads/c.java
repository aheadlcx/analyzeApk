package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import com.baidu.mobads.component.XAdView.Listener;

class c implements Listener {
    final /* synthetic */ AdView a;

    c(AdView adView) {
        this.a = adView;
    }

    public void onWindowVisibilityChanged(int i) {
        AdView.b(this.a).a(i);
    }

    public void onWindowFocusChanged(boolean z) {
        AdView.b(this.a).a(z);
    }

    public void onLayoutComplete(int i, int i2) {
        AdView.c(this.a);
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
        AdView.b(this.a).o();
    }

    public void onAttachedToWindow() {
        AdView.c(this.a);
        AdView.b(this.a).n();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return AdView.b(this.a).a(i, keyEvent);
    }
}
