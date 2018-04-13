package com.ak.android.engine.nav;

import android.app.Activity;
import android.view.View;
import com.ak.android.engine.navbase.NativeAdListener;
import org.json.JSONObject;

public interface NativeAd {
    JSONObject getAPPInfo();

    int getAPPStatus();

    int getActionType();

    String getAdSpaceId();

    JSONObject getContent();

    int getProgress();

    void onAdClick(Activity activity, View view);

    void onAdClosed();

    void onAdShowed(View view);

    void setAdListener(NativeAdListener nativeAdListener);
}
