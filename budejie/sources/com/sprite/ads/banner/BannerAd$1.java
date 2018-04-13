package com.sprite.ads.banner;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.a.a;
import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import java.util.Map;

class BannerAd$1 implements a {
    final /* synthetic */ BannerAd this$0;

    BannerAd$1(BannerAd bannerAd) {
        this.this$0 = bannerAd;
    }

    public void loadFailure(final ErrorCode errorCode) {
        BannerAd.access$800(this.this$0, new Runnable() {
            public void run() {
                BannerAd.access$300(BannerAd$1.this.this$0).onNoAD(errorCode.getErrorCode());
            }
        });
    }

    public void loadSuccess(ResponseBody responseBody) {
        BannerAd.access$002(this.this$0, responseBody.config);
        String str = (String) BannerAd.access$000(this.this$0).sequence.get(0);
        Map map = responseBody.data;
        DataSourceType dataSourceType = DataSourceType.getDataSourceType(str);
        if (dataSourceType == DataSourceType.SELF) {
            BannerAd.access$102(this.this$0, BannerFactory.createBannerAd(dataSourceType, BannerAd.access$200(this.this$0, BannerAd.access$000(this.this$0).sequence, map), BannerAd.access$000(this.this$0)));
        } else {
            BannerAd.access$102(this.this$0, BannerFactory.createBannerAd(dataSourceType, (AdItem) map.get(str), BannerAd.access$000(this.this$0)));
        }
        if (BannerAd.access$100(this.this$0) == null) {
            BannerAd.access$400(this.this$0, new Runnable() {
                public void run() {
                    BannerAd.access$300(BannerAd$1.this.this$0).onNoAD(0);
                }
            });
        } else {
            BannerAd.access$700(this.this$0, new Runnable() {
                public void run() {
                    BannerAd.access$100(BannerAd$1.this.this$0).loadAd(BannerAd.access$500(BannerAd$1.this.this$0), BannerAd.access$600(BannerAd$1.this.this$0), BannerAd.access$300(BannerAd$1.this.this$0));
                }
            });
        }
    }
}
