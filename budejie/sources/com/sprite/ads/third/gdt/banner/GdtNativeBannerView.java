package com.sprite.ads.third.gdt.banner;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.banner.ThirdBannerViewBase;
import com.sprite.ads.internal.utils.AdUtil;
import com.sprite.ads.third.gdt.nati.GdtNativeAdData;
import com.sprite.ads.third.gdt.nati.GdtNativeReporter;

public class GdtNativeBannerView extends ThirdBannerViewBase<GdtNativeAdData> implements OnClickListener {
    public GdtNativeBannerView(Context context, GdtNativeAdData gdtNativeAdData, ViewGroup viewGroup, BannerADListener bannerADListener) {
        super(context, viewGroup, new GdtNativeReporter(gdtNativeAdData), gdtNativeAdData, bannerADListener);
    }

    protected void createBannerView() {
        super.createBannerView();
        AdUtil.addAdCaption(getContext(), this.mParentLayout);
        AdUtil.addGdtCaption(getContext(), this.mParentLayout);
    }
}
