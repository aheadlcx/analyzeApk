package com.sprite.ads.third.baidu.banner;

import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.banner.ThirdBannerViewBase;
import com.sprite.ads.internal.utils.AdUtil;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.baidu.nati.BaiduNativeAdData;

public class BaiduBannerView extends ThirdBannerViewBase<BaiduNativeAdData> {
    public BaiduBannerView(Context context, ViewGroup viewGroup, Reporter reporter, BaiduNativeAdData baiduNativeAdData, BannerADListener bannerADListener) {
        super(context, viewGroup, reporter, baiduNativeAdData, bannerADListener);
    }

    protected void createBannerView() {
        super.createBannerView();
        AdUtil.addAdCaption(getContext(), this.mParentLayout);
    }
}
