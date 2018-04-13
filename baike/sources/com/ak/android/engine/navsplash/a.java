package com.ak.android.engine.navsplash;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class a extends com.ak.android.engine.navbase.a {
    public a(NativeSplashAdLoaderListener nativeSplashAdLoaderListener) {
        super(nativeSplashAdLoaderListener);
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.z /*53051*/:
                    ((NativeSplashAdLoaderListener) this.a).onAdLoadSuccess(new b((DynamicObject) objArr[0]));
                    break;
                default:
                    return super.invoke(i, objArr);
            }
        }
        return null;
    }
}
