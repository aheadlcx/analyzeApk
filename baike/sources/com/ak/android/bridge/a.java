package com.ak.android.bridge;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import com.ak.android.base.landingpage.ActivityBridge;
import com.ak.android.base.landingpage.ILandingPageView;
import com.ak.android.base.landingpage.b;
import com.ak.android.engine.download.ApkListener;
import com.ak.android.engine.nav.NativeAdLoaderListener;
import com.ak.android.engine.navbase.c;
import com.ak.android.engine.navsplash.NativeSplashAdLoaderListener;
import com.ak.android.engine.navvideo.NativeVideoAd;
import com.ak.android.engine.navvideo.NativeVideoAdLoaderListener;
import java.util.ArrayList;

public final class a implements IBridge {
    private final DynamicObject a;

    public a(DynamicObject dynamicObject) {
        this.a = dynamicObject;
    }

    public final ContentProvider getProvider() {
        if (this.a != null) {
            return (ContentProvider) this.a.invoke(d.n, new Object[0]);
        }
        return null;
    }

    public final void initSdk(Context context) {
        if (this.a != null) {
            this.a.invoke(d.a, context);
        }
    }

    public final void setConfig(boolean z, boolean z2) {
        if (this.a != null) {
            this.a.invoke(d.b, Boolean.valueOf(z), Boolean.valueOf(z2));
        }
    }

    public final void setChannel(String str) {
        if (this.a != null) {
            this.a.invoke(d.i, str);
        }
    }

    public final ActivityBridge getActivityBridge() {
        if (this.a != null) {
            return (ActivityBridge) this.a.invoke(d.d, new Object[0]);
        }
        return null;
    }

    public final void setLandingPageView(ILandingPageView iLandingPageView) {
        if (this.a == null) {
            return;
        }
        if (iLandingPageView == null) {
            this.a.invoke(d.c, new Object[0]);
            return;
        }
        this.a.invoke(d.c, new b(iLandingPageView));
    }

    public final Object getNativeAdLoader(Context context, NativeAdLoaderListener nativeAdLoaderListener, ArrayList<Object[]> arrayList) {
        if (this.a == null) {
            return null;
        }
        return new c((DynamicObject) this.a.invoke(d.j, context, new com.ak.android.engine.nav.a(nativeAdLoaderListener), arrayList));
    }

    public final void getNativeSplashAd(Context context, String str, int i, NativeSplashAdLoaderListener nativeSplashAdLoaderListener) {
        if (this.a != null) {
            this.a.invoke(d.g, context, str, Integer.valueOf(i), new com.ak.android.engine.navsplash.a(nativeSplashAdLoaderListener));
        }
    }

    public final Object getNativeVideoAdLoader(Context context, NativeVideoAdLoaderListener nativeVideoAdLoaderListener, ArrayList<Object[]> arrayList) {
        if (this.a == null) {
            return null;
        }
        return new com.ak.android.engine.navvideo.b((DynamicObject) this.a.invoke(d.k, context, new com.ak.android.engine.navvideo.a(nativeVideoAdLoaderListener), arrayList));
    }

    public final void downloadRelevant(Object... objArr) {
        if (this.a != null) {
            this.a.invoke(d.ao, objArr);
        }
    }

    public final void setApkListener(ApkListener apkListener) {
        if (this.a == null) {
            return;
        }
        if (apkListener == null) {
            this.a.invoke(d.l, new Object[0]);
            return;
        }
        this.a.invoke(d.l, new com.ak.android.engine.download.a(apkListener));
    }

    public final Object getVideoAdPlayer(Activity activity, NativeVideoAd nativeVideoAd, boolean z) {
        if (this.a == null) {
            return null;
        }
        return new com.ak.android.player.b((DynamicObject) this.a.invoke(d.m, activity, nativeVideoAd, Boolean.valueOf(z)));
    }
}
