package com.flurry.android;

final class ae implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ u b;

    ae(u uVar, String str) {
        this.b = uVar;
        this.a = str;
    }

    public final void run() {
        CallbackEvent callbackEvent = new CallbackEvent(101);
        callbackEvent.setMessage(this.a);
        this.b.y.onMarketAppLaunchError(callbackEvent);
    }
}
