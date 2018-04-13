package com.sprite.ads.third.baidu.nati;

import android.content.Context;
import android.util.Log;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.BaiduNative$BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.RequestParameters$Builder;
import com.baidu.mobads.AdView;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import com.sprite.ads.third.baidu.BaiduConstants;
import java.util.ArrayList;
import java.util.List;

public class BaiduNativeAdLoader extends ThirdAdLoader implements BaiduConstants {
    private static BaiduNativeAdLoader loader = null;
    List<NativeAdData> nativeAdDataList = new ArrayList();

    public BaiduNativeAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    public static BaiduNativeAdLoader getLoader(Context context, AdItem adItem) {
        if (loader == null) {
            loader = new BaiduNativeAdLoader(context, adItem);
        }
        return loader;
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        return new BaiduNativeReporter((BaiduNativeAdData) nativeAdData);
    }

    public void loadAd(Context context, AdItem adItem) {
        ThirdSdkItem thirdSdkItem = (ThirdSdkItem) adItem;
        AdView.setAppSid(context, thirdSdkItem.aid);
        Log.i(BaiduNativeAdLoader.class.getName(), ((ThirdSdkItem) adItem).aid + "--");
        new BaiduNative(context, thirdSdkItem.pid, new BaiduNative$BaiduNativeNetworkListener() {
            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                Log.i(BaiduNativeAdLoader.class.getName(), nativeErrorCode.name());
                BaiduNativeAdLoader.this.setIsInit(true);
                BaiduNativeAdLoader.this.adLoadFailed();
            }

            public void onNativeLoad(List<NativeResponse> list) {
                Log.i(BaiduNativeAdLoader.class.getName(), list.size() + " ddd");
                if (list != null && list.size() > 0) {
                    BaiduNativeAdLoader.this.nativeAdDataList.clear();
                    for (NativeResponse baiduNativeAdData : list) {
                        BaiduNativeAdLoader.this.nativeAdDataList.add(new BaiduNativeAdData(baiduNativeAdData));
                    }
                    BaiduNativeAdLoader.this.addThirdToPool(BaiduNativeAdLoader.this.nativeAdDataList);
                }
            }
        }).makeRequest(new RequestParameters$Builder().downloadAppConfirmPolicy(2).build());
    }

    public void release() {
    }
}
