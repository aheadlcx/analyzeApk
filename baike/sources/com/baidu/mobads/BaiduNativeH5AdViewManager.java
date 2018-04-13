package com.baidu.mobads;

import android.content.Context;

public class BaiduNativeH5AdViewManager {
    private static BaiduNativeH5AdViewManager a;

    private BaiduNativeH5AdViewManager() {
    }

    public static synchronized BaiduNativeH5AdViewManager getInstance() {
        BaiduNativeH5AdViewManager baiduNativeH5AdViewManager;
        synchronized (BaiduNativeH5AdViewManager.class) {
            if (a == null) {
                a = new BaiduNativeH5AdViewManager();
            }
            baiduNativeH5AdViewManager = a;
        }
        return baiduNativeH5AdViewManager;
    }

    public BaiduNativeH5AdView getBaiduNativeH5AdView(Context context, BaiduNativeAdPlacement baiduNativeAdPlacement, int i) {
        BaiduNativeH5AdView b = baiduNativeAdPlacement.b();
        if (baiduNativeAdPlacement.b() != null) {
            return b;
        }
        b = new BaiduNativeH5AdView(context, i);
        b.setAdPlacement(baiduNativeAdPlacement);
        baiduNativeAdPlacement.a(b);
        return b;
    }
}
