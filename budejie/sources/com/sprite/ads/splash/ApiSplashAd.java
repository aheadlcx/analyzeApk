package com.sprite.ads.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem2;
import com.sprite.ads.internal.bean.data.ThirdItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.splash.ApiSpriteSplashView.Builder;
import com.sprite.ads.third.ThirdAdLoader;
import com.sprite.ads.third.ThirdApiAdData;
import com.sprite.ads.third.ThirdApiAdLoader;
import com.sprite.ads.third.ThirdApiAdLoader2;

public class ApiSplashAd extends SplashAdapter {
    private ThirdAdLoader adLoader;

    public ApiSplashAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void loadAd(Activity activity, ViewGroup viewGroup, SplashADListener splashADListener) {
        ThirdApiAdData thirdApiAdData = (ThirdApiAdData) this.adLoader.getNativeAdData();
        if (thirdApiAdData != null) {
            new Builder(activity).setSplashADListener(splashADListener).setParentLayout(viewGroup).setData(this.mAdItem).setNativeAdData(thirdApiAdData).setReporter(this.adLoader.getAdReporter(thirdApiAdData, this.mAdItem.downwarn)).build().show();
        } else if (this.adLoader.isInit()) {
            AdItem defAdItem = ((ThirdItem) this.mAdItem).getDefAdItem();
            if (defAdItem == null || defAdItem.postId == null) {
                splashADListener.onNoAD(0);
            } else {
                new SpriteSplashView.Builder(activity).setSplashADListener(splashADListener).setParentLayout(viewGroup).setData(defAdItem).setConfig(this.mAdConfig).build().show();
            }
        }
    }

    public void release() {
    }

    public void show(final Activity activity, final ViewGroup viewGroup, final SplashADListener splashADListener) {
        if (this.mAdItem instanceof ThirdApiItem2) {
            this.adLoader = new ThirdApiAdLoader2(activity, this.mAdItem);
        } else {
            this.adLoader = new ThirdApiAdLoader(activity, this.mAdItem);
        }
        this.adLoader.setLoaderListener(new AdLoaderListener() {
            public void loadFailed() {
                if (splashADListener != null) {
                    splashADListener.onNoAD(0);
                }
            }

            public void loadSuccess() {
                try {
                    if (activity == null || !activity.isFinishing()) {
                        ApiSplashAd.this.loadAd(activity, viewGroup, splashADListener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (splashADListener != null) {
                        splashADListener.onNoAD(0);
                    }
                }
            }
        });
        this.adLoader.loadAd(activity, this.mAdItem);
    }
}
