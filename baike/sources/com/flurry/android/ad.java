package com.flurry.android;

final class ad implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ u b;

    ad(u uVar, int i) {
        this.b = uVar;
        this.a = i;
    }

    public final void run() {
        this.b.y.onAdsUpdated(new CallbackEvent(this.a));
    }
}
