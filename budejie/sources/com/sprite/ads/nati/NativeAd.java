package com.sprite.ads.nati;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import com.sprite.ads.BaseAd;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.a;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.loader.NativeAdLoader;
import com.sprite.ads.nati.loader.NativeAdLoaderFactory;
import com.sprite.ads.nati.view.NativeAdLoaderRef;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class NativeAd extends BaseAd {
    private List<AdItem> preLoadMovieList = new ArrayList();

    public NativeAd(Activity activity) {
        this.mActivity = activity;
        this.adRequest = new a();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    private List<NativeAdRef> getAdItemRefList(Map<String, AdItem> map, ADConfig aDConfig, NativeADListener nativeADListener) {
        List<NativeAdRef> arrayList = new ArrayList();
        try {
            int i;
            int i2;
            int i3;
            if (aDConfig.params != null) {
                i = aDConfig.params.first;
                i2 = aDConfig.params.stepinc;
                i3 = aDConfig.params.interval;
            } else {
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            int i4 = 0;
            for (int i5 = 0; i5 < aDConfig.sequence.size(); i5++) {
                AdItem adItem = (AdItem) map.get((String) aDConfig.sequence.get(i5));
                if (adItem != null) {
                    if ("movie".equals(adItem.getResType())) {
                        this.preLoadMovieList.add(adItem);
                    }
                    com.sprite.ads.nati.a.a aVar = new com.sprite.ads.nati.a.a();
                    aVar.a(nativeADListener);
                    aVar.a(adItem);
                    aVar.b(adItem.downwarn);
                    i4 = getPosition(i4, i, i2, i3, i5);
                    aVar.a(i4);
                    initThirdData(aVar);
                    arrayList.add(aVar);
                }
            }
            return arrayList;
        } catch (Exception e) {
            return arrayList;
        }
    }

    private int getPosition(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return i2 == 0 ? i4 : i2;
        } else {
            if (i4 != 0) {
                i4++;
            }
            return (i + i4) + (i3 * i5);
        }
    }

    private void initThirdData(com.sprite.ads.nati.a.a aVar) {
        AdItem b = aVar.b();
        DataSourceType dataSourceType = b.getDataSourceType();
        if (a.a().a(b.postId) == null) {
            NativeAdLoader createAdLoader = NativeAdLoaderFactory.createAdLoader(dataSourceType, this.mActivity, b);
            if (createAdLoader != null) {
                createAdLoader.startRefreshTask();
                a.a().a(b.postId, new NativeAdLoaderRef(aVar, createAdLoader));
            }
        }
    }

    public void loadAd(String str, NativeADListener nativeADListener) {
        this.adRequest.a(str, new NativeAd$1(this, nativeADListener));
    }

    public void loadAd(String str, NativeSingleAdListener nativeSingleAdListener) {
        loadAd(str, new NativeAd$2(this, nativeSingleAdListener));
    }
}
