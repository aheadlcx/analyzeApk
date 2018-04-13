package com.ak.android.engine.navbase;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public class a implements DynamicObject {
    protected BaseNativeAdLoaderListener a;

    public a(BaseNativeAdLoaderListener baseNativeAdLoaderListener) {
        this.a = baseNativeAdLoaderListener;
    }

    public Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.A /*53052*/:
                    this.a.onAdLoadFailed(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                    break;
            }
        }
        return null;
    }
}
