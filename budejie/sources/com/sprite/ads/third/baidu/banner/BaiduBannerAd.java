package com.sprite.ads.third.baidu.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.banner.BannerAdapter;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.third.baidu.nati.BaiduNativeAdData;
import com.sprite.ads.third.baidu.nati.BaiduNativeAdLoader;
import com.sprite.ads.third.baidu.nati.BaiduNativeReporter;

public class BaiduBannerAd extends BannerAdapter {
    BaiduNativeAdLoader adLoader;

    public BaiduBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void loadAd(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        final ThirdSdkItem thirdSdkItem = (ThirdSdkItem) this.mAdItem;
        this.adLoader = new BaiduNativeAdLoader(context, thirdSdkItem);
        final Context context2 = context;
        final ViewGroup viewGroup2 = viewGroup;
        final BannerADListener bannerADListener2 = bannerADListener;
        this.adLoader.setLoaderListener(new AdLoaderListener() {
            public void loadSuccess() {
                try {
                    if (context2 == null || !(context2 instanceof Activity) || !((Activity) context2).isFinishing()) {
                        BaiduNativeAdData baiduNativeAdData = (BaiduNativeAdData) BaiduBannerAd.this.adLoader.getNativeAdData();
                        baiduNativeAdData.screenRatio = thirdSdkItem.screen_ratio;
                        BaiduBannerView baiduBannerView = new BaiduBannerView(context2, viewGroup2, new BaiduNativeReporter(baiduNativeAdData), baiduNativeAdData, bannerADListener2);
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
