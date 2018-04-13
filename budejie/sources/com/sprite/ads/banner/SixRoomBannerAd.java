package com.sprite.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SixRoomItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.third.sixroom.SixRoomAdData;
import com.sprite.ads.third.sixroom.SixRoomAdLoader;

public class SixRoomBannerAd extends BannerAdapter {
    SixRoomAdLoader adLoader;

    public SixRoomBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void loadAd(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        final SixRoomItem sixRoomItem = (SixRoomItem) this.mAdItem;
        this.adLoader = new SixRoomAdLoader(context, sixRoomItem);
        final Context context2 = context;
        final ViewGroup viewGroup2 = viewGroup;
        final BannerADListener bannerADListener2 = bannerADListener;
        this.adLoader.setLoaderListener(new AdLoaderListener() {
            public void loadFailed() {
                if (bannerADListener2 != null) {
                    bannerADListener2.onNoAD(0);
                }
            }

            public void loadSuccess() {
                try {
                    if (context2 == null || !(context2 instanceof Activity) || !((Activity) context2).isFinishing()) {
                        SixRoomBannerView sixRoomBannerView = new SixRoomBannerView(SixRoomBannerAd.this.adLoader, sixRoomItem, (SixRoomAdData) SixRoomBannerAd.this.adLoader.getNativeAdData(), context2, viewGroup2, bannerADListener2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bannerADListener2 != null) {
                        bannerADListener2.onNoAD(0);
                    }
                }
            }
        });
        this.adLoader.loadAd(context, this.mAdItem);
    }
}
