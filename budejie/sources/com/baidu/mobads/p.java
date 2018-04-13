package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import com.baidu.mobads.component.XAdView.Listener;

class p implements Listener {
    final /* synthetic */ InterstitialAd a;

    p(InterstitialAd interstitialAd) {
        this.a = interstitialAd;
    }

    public void onWindowVisibilityChanged(int i) {
    }

    public void onWindowFocusChanged(boolean z) {
    }

    public void onLayoutComplete(int i, int i2) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return InterstitialAd.b(this.a).a(i, keyEvent);
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
    }

    public void onAttachedToWindow() {
    }
}
