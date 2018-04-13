package com.ak.android.bridge;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import com.ak.android.base.landingpage.ActivityBridge;
import com.ak.android.base.landingpage.ILandingPageView;
import com.ak.android.engine.download.ApkListener;
import com.ak.android.engine.nav.NativeAdLoaderListener;
import com.ak.android.engine.navsplash.NativeSplashAdLoaderListener;
import com.ak.android.engine.navvideo.NativeVideoAd;
import com.ak.android.engine.navvideo.NativeVideoAdLoaderListener;
import java.util.ArrayList;

public interface IBridge {
    void downloadRelevant(Object... objArr);

    ActivityBridge getActivityBridge();

    Object getNativeAdLoader(Context context, NativeAdLoaderListener nativeAdLoaderListener, ArrayList<Object[]> arrayList);

    void getNativeSplashAd(Context context, String str, int i, NativeSplashAdLoaderListener nativeSplashAdLoaderListener);

    Object getNativeVideoAdLoader(Context context, NativeVideoAdLoaderListener nativeVideoAdLoaderListener, ArrayList<Object[]> arrayList);

    ContentProvider getProvider();

    Object getVideoAdPlayer(Activity activity, NativeVideoAd nativeVideoAd, boolean z);

    void initSdk(Context context);

    void setApkListener(ApkListener apkListener);

    void setChannel(String str);

    void setConfig(boolean z, boolean z2);

    void setLandingPageView(ILandingPageView iLandingPageView);
}
