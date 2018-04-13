package com.sprite.ads.nati.loader;

import android.content.Context;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem2;
import com.sprite.ads.third.ThirdApiAdLoader;
import com.sprite.ads.third.ThirdApiAdLoader2;
import com.sprite.ads.third.book.BookAdLoader;
import com.sprite.ads.third.linkactive.LinkActiveAdLoader;
import com.sprite.ads.third.sixroom.SixRoomAdLoader;

public class NativeAdLoaderFactory {
    public static NativeAdLoader create(DataSourceType dataSourceType, Context context, AdItem adItem) {
        try {
            switch (dataSourceType) {
                case SELF:
                    return new SelfAdLoader();
                case SDK_GDT:
                    return create("com.sprite.ads.third.gdt.nati.GdtNativeAdLoader", context, adItem);
                case API_GDT:
                    return adItem instanceof ThirdApiItem2 ? new ThirdApiAdLoader2(context, adItem) : new ThirdApiAdLoader(context, adItem);
                case API_ACTIVE:
                    return new LinkActiveAdLoader(context, adItem);
                case SDK_360:
                    return create("com.sprite.ads.third.qh.nati.QHNativeAdLoader", context, adItem);
                case SDK_MINTEGRAL:
                    return create("com.sprite.ads.third.mintegral.nati.MintegralNativeAdLoader", context, adItem);
                case SDK_BAIDU:
                    return create("com.sprite.ads.third.baidu.nati.BaiduNativeAdLoader", context, adItem);
                case SDK_CM:
                    return create("com.sprite.ads.third.cmad.nati.CmNativeAdLoader", context, adItem);
                case SDK_TOUTIAO:
                    return create("com.sprite.ads.third.toutiao.nati.ToutiaoNativeAdLoader", context, adItem);
                case SELF_SIX_ROOM:
                    return new SixRoomAdLoader(context, adItem);
                case SELF_HUAXI_BOOK:
                    return new BookAdLoader(context, adItem);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static NativeAdLoader create(String str, Context context, AdItem adItem) {
        return (NativeAdLoader) Class.forName(str).getConstructor(new Class[]{Context.class, AdItem.class}).newInstance(new Object[]{context, adItem});
    }

    public static NativeAdLoader createAdLoader(DataSourceType dataSourceType, Context context, AdItem adItem) {
        return create(dataSourceType, context, adItem);
    }
}
