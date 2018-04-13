package com.sprite.ads;

import android.app.Activity;
import android.os.Handler;

public class BaseAd {
    protected a adRequest;
    protected Activity mActivity;
    protected Handler mainHandler;

    protected void runOnMainThread(Runnable runnable) {
        this.mainHandler.post(runnable);
    }
}
