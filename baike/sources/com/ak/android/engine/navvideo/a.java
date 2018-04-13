package com.ak.android.engine.navvideo;

import com.ak.android.bridge.d;

public final class a extends com.ak.android.engine.navbase.a {
    public a(NativeVideoAdLoaderListener nativeVideoAdLoaderListener) {
        super(nativeVideoAdLoaderListener);
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.z /*53051*/:
                    ((NativeVideoAdLoaderListener) this.a).onAdLoadSuccess(NavV.getNativeVideoAds(objArr[0]));
                    break;
                default:
                    return super.invoke(i, objArr);
            }
        }
        return null;
    }
}
