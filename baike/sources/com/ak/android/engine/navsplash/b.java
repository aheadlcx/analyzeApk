package com.ak.android.engine.navsplash;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.engine.navbase.d;

public final class b extends d implements NativeSplashAd {
    public b(DynamicObject dynamicObject) {
        super(dynamicObject);
    }

    public final boolean isLinked() {
        if (this.a != null) {
            return ((Boolean) this.a.invoke(com.ak.android.bridge.d.S, new Object[0])).booleanValue();
        }
        return false;
    }
}
