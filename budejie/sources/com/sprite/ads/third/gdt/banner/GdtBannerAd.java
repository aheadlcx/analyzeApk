package com.sprite.ads.third.gdt.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.banner.BannerAdapter;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.third.gdt.nati.GdtNativeAdData;
import com.sprite.ads.third.gdt.nati.GdtNativeAdLoader;

public class GdtBannerAd extends BannerAdapter {
    GdtNativeAdLoader adLoader;

    public GdtBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void loadAd(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        final ThirdSdkItem thirdSdkItem = (ThirdSdkItem) this.mAdItem;
        this.adLoader = new GdtNativeAdLoader(context, thirdSdkItem);
        this.adLoader.setQueryCount(1);
        final Context context2 = context;
        final ViewGroup viewGroup2 = viewGroup;
        final BannerADListener bannerADListener2 = bannerADListener;
        this.adLoader.setLoaderListener(new AdLoaderListener() {
            public void loadSuccess() {
                try {
                    if (context2 == null || !(context2 instanceof Activity) || !((Activity) context2).isFinishing()) {
                        GdtNativeAdData gdtNativeAdData = (GdtNativeAdData) GdtBannerAd.this.adLoader.getNativeAdData();
                        gdtNativeAdData.screenRatio = thirdSdkItem.screen_ratio;
                        GdtNativeBannerView gdtNativeBannerView = new GdtNativeBannerView(context2, gdtNativeAdData, viewGroup2, bannerADListener2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bannerADListener2 != null) {
                        bannerADListener2.onNoAD(0);
                    }
                }
            }

            public void loadFailed() {
                if (bannerADListener2 != null) {
                    bannerADListener2.onNoAD(0);
                }
            }
        });
        this.adLoader.loadAd(context, thirdSdkItem);
    }
}
