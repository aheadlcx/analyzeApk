package com.ak.android.shell;

import android.content.Context;
import android.support.annotation.Nullable;
import com.ak.android.base.landingpage.ILandingPageView;
import com.ak.android.bridge.IBridge;
import com.ak.android.bridge.b;
import com.ak.android.bridge.c;
import com.ak.android.engine.download.ApkListener;
import com.ak.android.engine.nav.NativeAdLoaderListener;
import com.ak.android.engine.navbase.AdSpace;
import com.ak.android.engine.navbase.NativeAdLoader;
import com.ak.android.engine.navsplash.NativeSplashAdLoaderListener;
import com.ak.android.engine.navvideo.NativeVideoAdLoader;
import com.ak.android.engine.navvideo.NativeVideoAdLoaderListener;
import java.util.ArrayList;

public final class AKAD {
    public static final int LOAD_TYPE_ALL = 0;
    public static final int LOAD_TYPE_OFFLINE = 1;
    protected static IBridge bridge;

    public static void initSdk(Context context, boolean z, boolean z2) {
        b.m = z;
        checkBridge(context);
        if (bridge != null) {
            bridge.setConfig(z, z2);
        }
    }

    public static void setChannel(Context context, String str) {
        checkBridge(context);
        if (bridge != null) {
            bridge.setChannel(str);
        }
    }

    public static void setLandingPageView(Context context, ILandingPageView iLandingPageView) {
        checkBridge(context);
        if (bridge != null) {
            bridge.setLandingPageView(iLandingPageView);
        }
    }

    @Nullable
    public static NativeAdLoader getNativeAdLoader(Context context, String str, NativeAdLoaderListener nativeAdLoaderListener) {
        return getNativeAdLoader(context, nativeAdLoaderListener, new AdSpace(str));
    }

    @Nullable
    public static NativeAdLoader getNativeAdLoader(Context context, NativeAdLoaderListener nativeAdLoaderListener, AdSpace... adSpaceArr) {
        ArrayList adSpacesArgs = getAdSpacesArgs(context, adSpaceArr);
        if (adSpacesArgs != null) {
            return (NativeAdLoader) bridge.getNativeAdLoader(context, nativeAdLoaderListener, adSpacesArgs);
        }
        return null;
    }

    public static NativeVideoAdLoader getNativeVideoAdLoader(Context context, String str, NativeVideoAdLoaderListener nativeVideoAdLoaderListener) {
        return getNativeVideoAdLoader(context, nativeVideoAdLoaderListener, new AdSpace(str));
    }

    public static NativeVideoAdLoader getNativeVideoAdLoader(Context context, NativeVideoAdLoaderListener nativeVideoAdLoaderListener, AdSpace... adSpaceArr) {
        ArrayList adSpacesArgs = getAdSpacesArgs(context, adSpaceArr);
        if (adSpacesArgs != null) {
            return (NativeVideoAdLoader) bridge.getNativeVideoAdLoader(context, nativeVideoAdLoaderListener, adSpacesArgs);
        }
        return null;
    }

    public static void getNativeSplashAd(Context context, String str, int i, NativeSplashAdLoaderListener nativeSplashAdLoaderListener) {
        checkBridge(context);
        if (bridge != null) {
            bridge.getNativeSplashAd(context, str, i, nativeSplashAdLoaderListener);
        }
    }

    private static ArrayList<Object[]> getAdSpacesArgs(Context context, AdSpace... adSpaceArr) {
        checkBridge(context);
        if (bridge == null || adSpaceArr == null || adSpaceArr.length <= 0) {
            return null;
        }
        ArrayList<Object[]> arrayList = new ArrayList();
        for (AdSpace args : adSpaceArr) {
            arrayList.add(args.getArgs());
        }
        return arrayList;
    }

    public static void setApkListener(Context context, ApkListener apkListener) {
        checkBridge(context);
        if (bridge != null) {
            bridge.setApkListener(apkListener);
        }
    }

    protected static void checkBridge(Context context) {
        if (bridge == null) {
            IBridge a = c.a(context);
            bridge = a;
            if (a != null) {
                bridge.initSdk(context);
            }
        }
    }

    private AKAD() {
    }
}
