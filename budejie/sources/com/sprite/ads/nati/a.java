package com.sprite.ads.nati;

import com.sprite.ads.nati.view.NativeAdLoaderRef;
import java.util.HashMap;
import java.util.Map;

public class a {
    private static a b;
    private Map<String, NativeAdLoaderRef> a = new HashMap();

    private a() {
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    public NativeAdLoaderRef a(String str) {
        return (NativeAdLoaderRef) this.a.get(str);
    }

    public void a(String str, NativeAdLoaderRef nativeAdLoaderRef) {
        this.a.put(str, nativeAdLoaderRef);
    }
}
