package com.flurry.android;

public interface AppCircleCallback {
    void onAdsUpdated(CallbackEvent callbackEvent);

    void onMarketAppLaunchError(CallbackEvent callbackEvent);
}
