package com.sprite.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.log.ADLog;
import java.util.Map;

public class SelfBannerAd extends BannerAdapter {
    Map<String, AdItem> mData;

    public SelfBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public SelfBannerAd(Map<String, AdItem> map, ADConfig aDConfig) {
        this((AdItem) map.get(""), aDConfig);
        this.mData = map;
    }

    public void loadAd(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        if (!this.mData.isEmpty()) {
            if (context != null) {
                try {
                    if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bannerADListener != null) {
                        bannerADListener.onNoAD(0);
                        return;
                    }
                    return;
                }
            }
            ADLog.d("自售 Banner Ad 请求成功");
            BannerViewContainer bannerViewContainer = new BannerViewContainer(this.mData, context, viewGroup, bannerADListener);
        } else if (bannerADListener != null) {
            bannerADListener.onNoAD(15);
        }
    }
}
