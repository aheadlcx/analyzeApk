package com.sprite.ads.popup;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sprite.ads.BaseAd;
import com.sprite.ads.a;
import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.internal.log.ADLog;

public final class PopupAd extends BaseAd {
    long delayMillis = 5000;
    Context mContext;
    PopupAdCache popupAdCache;

    public PopupAd(String str, Context context) {
        this.mContext = context;
        this.adRequest = new a();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.popupAdCache = new PopupAdCache(this.mContext);
        loadAd(str);
    }

    private void loadAd(String str) {
        this.adRequest.a(str, new a.a() {
            public void loadFailure(ErrorCode errorCode) {
            }

            public void loadSuccess(ResponseBody responseBody) {
                ADLog.d("resp:" + responseBody);
                final ADConfig aDConfig = responseBody.config;
                final AdItem adItem = (AdItem) responseBody.data.get((String) aDConfig.sequence.get(0));
                if (!PopupAd.this.popupAdCache.getPrompted(adItem.postId)) {
                    PopupAd.this.mainHandler.postDelayed(new Runnable() {
                        public void run() {
                            new AdDialog(PopupAd.this.mContext, adItem, aDConfig).show(PopupAd.this.mContext);
                            PopupAd.this.popupAdCache.setPrompted(adItem.postId, true);
                        }
                    }, PopupAd.this.delayMillis);
                }
            }
        });
    }
}
