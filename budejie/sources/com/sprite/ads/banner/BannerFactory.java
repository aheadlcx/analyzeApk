package com.sprite.ads.banner;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import java.util.Map;

public class BannerFactory {
    private static BannerAdapter create(String str, AdItem adItem, ADConfig aDConfig) {
        return (BannerAdapter) Class.forName(str).getConstructor(new Class[]{AdItem.class, ADConfig.class}).newInstance(new Object[]{adItem, aDConfig});
    }

    public static BannerAdapter createBannerAd(DataSourceType dataSourceType, AdItem adItem, ADConfig aDConfig) {
        try {
            switch (dataSourceType) {
                case API_GDT:
                    return new GdtApiBannerAd(adItem, aDConfig);
                case SDK_GDT:
                    return create("com.sprite.ads.third.gdt.banner.GdtBannerAd", adItem, aDConfig);
                case SDK_BAIDU:
                    return create("com.sprite.ads.third.baidu.banner.BaiduBannerAd", adItem, aDConfig);
                case SDK_360:
                    return create("com.sprite.ads.third.qh.banner.QHBannerAd", adItem, aDConfig);
                case SDK_MINTEGRAL:
                    return create("com.sprite.ads.third.mintegral.banner.MintegralBannerAd", adItem, aDConfig);
                case SDK_TOUTIAO:
                    return create("com.sprite.ads.third.toutiao.banner.ToutiaoBannerAd", adItem, aDConfig);
                case SELF_SIX_ROOM:
                    return new SixRoomBannerAd(adItem, aDConfig);
                case SELF_HUAXI_BOOK:
                    return new BookBannerAd(adItem, aDConfig);
                case SELF:
                    return new SelfBannerAd(adItem, aDConfig);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static BannerAdapter createBannerAd(DataSourceType dataSourceType, Map<String, AdItem> map, ADConfig aDConfig) {
        return new SelfBannerAd((Map) map, aDConfig);
    }
}
