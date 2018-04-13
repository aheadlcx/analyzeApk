package com.sprite.ads.splash;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.a.a;
import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.internal.log.ADLog;

class SplashAd$1 implements a {
    final /* synthetic */ SplashAd this$0;

    SplashAd$1(SplashAd splashAd) {
        this.this$0 = splashAd;
    }

    public void loadFailure(final ErrorCode errorCode) {
        SplashAd.access$700(this.this$0, new Runnable() {
            public void run() {
                SplashAd.access$100(SplashAd$1.this.this$0).onNoAD(errorCode.getErrorCode());
            }
        });
    }

    public void loadSuccess(ResponseBody responseBody) {
        SplashAd.access$002(this.this$0, responseBody.config);
        String str = (String) SplashAd.access$000(this.this$0).sequence.get(0);
        DataSourceType dataSourceType = DataSourceType.getDataSourceType(str);
        AdItem adItem = (AdItem) responseBody.data.get(str);
        if (adItem == null) {
            ADLog.d("SplashAd 广告数据为空");
            SplashAd.access$200(this.this$0, new Runnable() {
                public void run() {
                    SplashAd.access$100(SplashAd$1.this.this$0).onNoAD(15);
                }
            });
            return;
        }
        final SplashAdapter createSplashAd = SplashFactory.createSplashAd(dataSourceType, adItem, SplashAd.access$000(this.this$0));
        if (createSplashAd == null) {
            ADLog.d("SplashAdapter 初始化失败");
            SplashAd.access$300(this.this$0, new Runnable() {
                public void run() {
                    SplashAd.access$100(SplashAd$1.this.this$0).onNoAD(10);
                }
            });
            return;
        }
        SplashAd.access$600(this.this$0, new Runnable() {
            public void run() {
                createSplashAd.show(SplashAd.access$400(SplashAd$1.this.this$0), SplashAd.access$500(SplashAd$1.this.this$0), SplashAd.access$100(SplashAd$1.this.this$0));
            }
        });
    }
}
