package com.ak.android.engine.navbase;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class b implements DynamicObject {
    private NativeAdListener a;

    public b(NativeAdListener nativeAdListener) {
        this.a = nativeAdListener;
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.B /*53101*/:
                    this.a.onAlertChange(((Integer) objArr[0]).intValue());
                    break;
                case d.C /*53102*/:
                    this.a.onLandingPageChange(((Integer) objArr[0]).intValue());
                    break;
                case d.D /*53103*/:
                    this.a.onLeftApplication();
                    break;
            }
        }
        return null;
    }
}
