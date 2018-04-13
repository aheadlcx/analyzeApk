package qsbk.app.activity.group;

import android.util.Log;
import com.baidu.mobads.SplashAdListener;
import qsbk.app.activity.group.SplashAdOtherActivity.BaiduAd;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class g implements SplashAdListener {
    final /* synthetic */ BaiduAd a;

    g(BaiduAd baiduAd) {
        this.a = baiduAd;
    }

    public void onAdDismissed() {
        Log.i("RSplashActivity", "onAdDismissed");
        this.a.gotoNext();
    }

    public void onAdFailed(String str) {
        Log.i("RSplashActivity", "onAdFailed");
        this.a.gotoMain();
    }

    public void onAdPresent() {
        SplashAdStatUtil.showBaidu();
        Log.i("RSplashActivity", "onAdPresent");
    }

    public void onAdClick() {
        Log.i("RSplashActivity", "onAdClick");
        ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.KAIPING);
    }
}
