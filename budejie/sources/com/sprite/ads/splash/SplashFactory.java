package com.sprite.ads.splash;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;

public class SplashFactory {
    private static SplashAdapter create(String str, AdItem adItem, ADConfig aDConfig) {
        return (SplashAdapter) Class.forName(str).getConstructor(new Class[]{AdItem.class, ADConfig.class}).newInstance(new Object[]{adItem, aDConfig});
    }

    public static SplashAdapter createSplashAd(DataSourceType dataSourceType, AdItem adItem, ADConfig aDConfig) {
        try {
            switch (dataSourceType) {
                case SDK_GDT:
                    return create("com.sprite.ads.third.gdt.splash.GdtSplashAd", adItem, aDConfig);
                case SDK_BAIDU:
                    return create("com.sprite.ads.third.baidu.splash.BaiduSplashAd", adItem, aDConfig);
                case SDK_MINTEGRAL:
                    return create("com.sprite.ads.third.mintegral.splash.MintegralSplashAd", adItem, aDConfig);
                case SDK_360:
                    return create("com.sprite.ads.third.qh.splash.QHSplashAd", adItem, aDConfig);
                case SDK_TOUTIAO:
                    return create("com.sprite.ads.third.toutiao.splash.ToutiaoSplashAd", adItem, aDConfig);
                case SELF:
                    return new SelfSplashAd(adItem, aDConfig);
                case API_GDT:
                    return new ApiSplashAd(adItem, aDConfig);
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        e.printStackTrace();
        return null;
    }
}
