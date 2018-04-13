package com.sprite.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem2;
import com.sprite.ads.internal.bean.data.ThirdItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.third.ThirdAdLoader;
import com.sprite.ads.third.ThirdApiAdData;
import com.sprite.ads.third.ThirdApiAdLoader;
import com.sprite.ads.third.ThirdApiAdLoader2;
import java.util.HashMap;
import java.util.Map;

public class GdtApiBannerAd extends BannerAdapter {
    private ThirdAdLoader adLoader;

    public GdtApiBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    private void show(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        ThirdApiAdData thirdApiAdData = (ThirdApiAdData) this.adLoader.getNativeAdData();
        if (thirdApiAdData != null) {
            GDTBannerView gDTBannerView = new GDTBannerView(this.adLoader, this.mAdItem.downwarn, thirdApiAdData, context, viewGroup, bannerADListener);
        } else if (this.adLoader.isInit()) {
            SelfItem defAdItem = ((ThirdItem) this.mAdItem).getDefAdItem();
            if (defAdItem == null || defAdItem.postId == null) {
                bannerADListener.onNoAD(0);
                return;
            }
            Map hashMap = new HashMap();
            hashMap.put(defAdItem.postId, defAdItem);
            BannerViewContainer bannerViewContainer = new BannerViewContainer(hashMap, context, viewGroup, bannerADListener);
        }
    }

    public void loadAd(final Context context, final ViewGroup viewGroup, final BannerADListener bannerADListener) {
        if (this.mAdItem instanceof ThirdApiItem2) {
            this.adLoader = new ThirdApiAdLoader2(null, this.mAdItem);
        } else {
            this.adLoader = new ThirdApiAdLoader(null, this.mAdItem);
        }
        this.adLoader.setLoaderListener(new AdLoaderListener() {
            public void loadFailed() {
                if (bannerADListener != null) {
                    bannerADListener.onNoAD(0);
                }
            }

            public void loadSuccess() {
                try {
                    if (context == null || !(context instanceof Activity) || !((Activity) context).isFinishing()) {
                        GdtApiBannerAd.this.show(context, viewGroup, bannerADListener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bannerADListener != null) {
                        bannerADListener.onNoAD(0);
                    }
                }
            }
        });
        this.adLoader.loadAd(null, this.mAdItem);
    }
}
