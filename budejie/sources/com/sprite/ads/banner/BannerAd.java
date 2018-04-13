package com.sprite.ads.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import com.sprite.ads.BaseAd;
import com.sprite.ads.a;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BannerAd extends BaseAd {
    private ADConfig adConfig;
    private BannerAdapter adapter;
    private Context mContext;
    private BannerADListener mListener;
    private ViewGroup mParentLayout;

    public BannerAd(String str, Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        this.mContext = context;
        this.mParentLayout = viewGroup;
        this.mListener = bannerADListener;
        this.adRequest = new a();
        this.mainHandler = new Handler(Looper.getMainLooper());
        loadAd(str);
    }

    private Map<String, AdItem> getAdData(List<String> list, Map<String, AdItem> map) {
        Map linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            linkedHashMap.put(str, map.get(str));
        }
        return linkedHashMap;
    }

    protected void loadAd(String str) {
        this.adRequest.a(str, new BannerAd$1(this));
    }
}
