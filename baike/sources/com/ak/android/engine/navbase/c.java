package com.ak.android.engine.navbase;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;
import java.util.HashMap;
import java.util.HashSet;

public class c implements NativeAdLoader {
    protected final DynamicObject a;

    public c(DynamicObject dynamicObject) {
        this.a = dynamicObject;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.invoke(d.w, new Object[0]);
        }
    }

    public void loadAds() {
        if (this.a != null) {
            this.a.invoke(d.v, new Object[0]);
        }
    }

    public void setExtras(HashMap<String, String> hashMap) {
        if (this.a != null) {
            this.a.invoke(d.y, hashMap);
        }
    }

    public void setKeyWords(HashSet<String> hashSet) {
        if (this.a != null) {
            this.a.invoke(d.x, hashSet);
        }
    }
}
