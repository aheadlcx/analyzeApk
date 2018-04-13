package com.sprite.ads.third.gdt.interstitial;

import android.app.Activity;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.comm.util.AdError;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.interstitial.InterstitalAdapter;

public class InterstitalAd implements InterstitalAdapter {
    public static String aid = "1104506376";
    public static String defInterteristalPosID = "4030703617764253";

    public InterstitalAd(AdItem adItem, ADConfig aDConfig) {
    }

    public void loadInterstitialAd(Activity activity) {
        loadAd(activity, defInterteristalPosID);
    }

    private void loadAd(Activity activity, String str) {
        final InterstitialAD interstitialAD = new InterstitialAD(activity, aid, str);
        interstitialAD.setADListener(new AbstractInterstitialADListener() {
            public void onNoAD(AdError adError) {
            }

            public void onADReceive() {
                interstitialAD.show();
            }
        });
        interstitialAD.loadAD();
    }

    public void loadInterstitialAd(Activity activity, String str) {
        loadAd(activity, str);
    }
}
