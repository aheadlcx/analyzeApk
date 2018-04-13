package com.sprite.ads.third.gdt.nati;

import android.content.Context;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeAD.NativeAdListener;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.qq.e.comm.util.AdError;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import com.sprite.ads.third.gdt.GDTConstants;
import java.util.ArrayList;
import java.util.List;

public class GdtNativeAdLoader extends ThirdAdLoader implements GDTConstants {
    List<NativeAdData> nativeAdDataList = new ArrayList();
    private int queryCount = 6;

    public void setQueryCount(int i) {
        this.queryCount = i;
    }

    public GdtNativeAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        return new GdtNativeReporter((GdtNativeAdData) nativeAdData);
    }

    public void loadAd(Context context, AdItem adItem) {
        ThirdSdkItem thirdSdkItem = (ThirdSdkItem) adItem;
        new NativeAD(context, thirdSdkItem.aid, thirdSdkItem.pid, new NativeAdListener() {
            public void onADLoaded(List<NativeADDataRef> list) {
                if (list != null && !list.isEmpty()) {
                    GdtNativeAdLoader.this.nativeAdDataList.clear();
                    for (NativeADDataRef gdtNativeAdData : list) {
                        GdtNativeAdLoader.this.nativeAdDataList.add(new GdtNativeAdData(gdtNativeAdData));
                    }
                    GdtNativeAdLoader.this.addThirdToPool(GdtNativeAdLoader.this.nativeAdDataList);
                }
            }

            public void onNoAD(AdError adError) {
                if (adError != null) {
                    ADLog.d("广电通广告——无广告：" + adError.getErrorCode() + " " + adError.getErrorMsg());
                }
                GdtNativeAdLoader.this.setIsInit(true);
                GdtNativeAdLoader.this.adLoadFailed();
            }

            public void onADStatusChanged(NativeADDataRef nativeADDataRef) {
            }

            public void onADError(NativeADDataRef nativeADDataRef, AdError adError) {
                if (adError != null) {
                    ADLog.d("广电通广告——出错：" + adError.getErrorCode() + " " + adError.getErrorMsg());
                }
                GdtNativeAdLoader.this.adLoadFailed();
            }
        }).loadAD(this.queryCount);
    }

    public void release() {
    }
}
