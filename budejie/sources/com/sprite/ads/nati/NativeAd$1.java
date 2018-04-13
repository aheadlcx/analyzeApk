package com.sprite.ads.nati;

import com.sprite.ads.a.a;
import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.internal.log.ADLog;
import java.util.List;

class NativeAd$1 implements a {
    final /* synthetic */ NativeADListener a;
    final /* synthetic */ NativeAd b;

    NativeAd$1(NativeAd nativeAd, NativeADListener nativeADListener) {
        this.b = nativeAd;
        this.a = nativeADListener;
    }

    public void loadFailure(final ErrorCode errorCode) {
        ADLog.d("native ad load failure");
        NativeAd.access$300(this.b, new Runnable(this) {
            final /* synthetic */ NativeAd$1 b;

            public void run() {
                this.b.a.loadFailure(errorCode);
            }
        });
    }

    public void loadSuccess(ResponseBody responseBody) {
        final List access$000 = NativeAd.access$000(this.b, responseBody.data, responseBody.config, this.a);
        NativeAd.access$200(this.b, new Runnable(this) {
            final /* synthetic */ NativeAd$1 b;

            public void run() {
                this.b.a.loadSuccess(access$000);
                if (!NativeAd.access$100(this.b.b).isEmpty()) {
                    this.b.a.preLoad(NativeAd.access$100(this.b.b));
                }
            }
        });
    }
}
