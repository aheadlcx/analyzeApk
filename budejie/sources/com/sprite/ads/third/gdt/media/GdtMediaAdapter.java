package com.sprite.ads.third.gdt.media;

import android.content.Context;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.media.MediaAdapter;
import com.sprite.ads.media.NativeMediaADListener;
import java.util.ArrayList;
import java.util.List;

public class GdtMediaAdapter extends MediaAdapter {
    ThirdSdkItem thirdSdkItem;

    public GdtMediaAdapter(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
        this.thirdSdkItem = (ThirdSdkItem) adItem;
    }

    public void loadAd(Context context, final NativeMediaADListener nativeMediaADListener) {
        NativeMediaAD nativeMediaAD = new NativeMediaAD(context, this.thirdSdkItem.aid, this.thirdSdkItem.pid, new NativeMediaAD.NativeMediaADListener() {
            public void onADLoaded(List<NativeMediaADData> list) {
                List arrayList = new ArrayList();
                for (NativeMediaADData gdtMediaAdItem : list) {
                    arrayList.add(new GdtMediaAdItem(gdtMediaAdItem));
                }
                nativeMediaADListener.onADLoaded(arrayList);
            }

            public void onNoAD(AdError adError) {
                nativeMediaADListener.onNoAD(adError.getErrorCode());
            }

            public void onADStatusChanged(NativeMediaADData nativeMediaADData) {
                nativeMediaADListener.onADStatusChanged(new GdtMediaAdItem(nativeMediaADData));
            }

            public void onADError(NativeMediaADData nativeMediaADData, AdError adError) {
            }

            public void onADVideoLoaded(NativeMediaADData nativeMediaADData) {
            }

            public void onADExposure(NativeMediaADData nativeMediaADData) {
            }

            public void onADClicked(NativeMediaADData nativeMediaADData) {
            }
        });
        nativeMediaAD.setBrowserType(BrowserType.Sys);
        nativeMediaAD.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.NOConfirm);
        nativeMediaAD.loadAD(6);
    }
}
