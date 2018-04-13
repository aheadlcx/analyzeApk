package qsbk.app.activity.group;

import android.util.Log;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;
import qsbk.app.activity.group.SplashAdOtherActivity.GDTAd;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class h implements SplashADListener {
    final /* synthetic */ GDTAd a;

    h(GDTAd gDTAd) {
        this.a = gDTAd;
    }

    public void onADDismissed() {
        Log.e("SplashAdOtherActivity", "onADDismissed: ");
        this.a.gotoNext();
    }

    public void onNoAD(AdError adError) {
        this.a.gotoMain();
    }

    public void onADTick(long j) {
    }

    public void onADClicked() {
        LogUtil.e("test gdt");
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.KAIPING);
    }

    public void onADPresent() {
        SplashAdStatUtil.showGDT();
        Log.e("SplashAdOtherActivity", "onADPresent: ");
    }
}
