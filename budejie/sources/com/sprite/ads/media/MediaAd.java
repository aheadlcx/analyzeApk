package com.sprite.ads.media;

import android.os.Handler;
import android.os.Looper;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.a;
import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.internal.log.ADLog;
import java.util.Map;

public final class MediaAd {
    a adRequest = new a();
    private NativeMediaAdView mMediaAdView;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public MediaAd(NativeMediaAdView nativeMediaAdView) {
        this.mMediaAdView = nativeMediaAdView;
    }

    public void loadAd(String str, final MediaAdListener mediaAdListener) {
        this.adRequest.a(str, new a.a() {
            public void loadFailure(final ErrorCode errorCode) {
                ADLog.d("native ad load failure");
                if (mediaAdListener != null) {
                    MediaAd.this.mainHandler.post(new Runnable() {
                        public void run() {
                            mediaAdListener.loadFailure(errorCode);
                        }
                    });
                }
            }

            public void loadSuccess(ResponseBody responseBody) {
                ADLog.d("native ad load success");
                final ADConfig aDConfig = responseBody.config;
                final String str = (String) aDConfig.sequence.get(0);
                final Map map = responseBody.data;
                final DataSourceType dataSourceType = DataSourceType.getDataSourceType(str);
                MediaAd.this.mainHandler.post(new Runnable() {
                    public void run() {
                        MediaAd.this.mMediaAdView.loadAd(dataSourceType, (AdItem) map.get(str), aDConfig);
                    }
                });
                if (mediaAdListener != null) {
                    mediaAdListener.loadSuccess((AdItem) map.get(str));
                }
            }
        });
    }
}
