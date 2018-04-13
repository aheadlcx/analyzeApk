package com.ak.android.engine.nav;

import com.ak.android.bridge.d;
import com.ak.android.engine.navbase.NavH;

public final class a extends com.ak.android.engine.navbase.a {
    public a(NativeAdLoaderListener nativeAdLoaderListener) {
        super(nativeAdLoaderListener);
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.z /*53051*/:
                    ((NativeAdLoaderListener) this.a).onAdLoadSuccess(NavH.getNativeAds(objArr[0]));
                    break;
                default:
                    return super.invoke(i, objArr);
            }
        }
        return null;
    }
}
