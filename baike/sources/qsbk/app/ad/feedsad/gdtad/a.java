package qsbk.app.ad.feedsad.gdtad;

import com.qq.e.ads.nativ.NativeMediaAD.NativeMediaADListener;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.util.AdError;
import java.util.List;
import qsbk.app.utils.DebugUtil;

class a implements NativeMediaADListener {
    final /* synthetic */ GdtAd a;

    a(GdtAd gdtAd) {
        this.a = gdtAd;
    }

    public void onADLoaded(List<NativeMediaADData> list) {
        for (int i = 0; i < list.size(); i++) {
            DebugUtil.debug(GdtAd.TAG, Thread.currentThread().getName() + " onFeedsADLoaded size：" + ((NativeMediaADData) list.get(i)).getTitle());
        }
        if (list == null || list.size() == 0) {
            this.a.reLoad();
            return;
        }
        this.a.onFeedsLoaded(list);
        this.a.isFetchingAd = false;
    }

    public void onNoAD(AdError adError) {
        DebugUtil.debug(GdtAd.TAG, " onFeedsADFail");
        this.a.reLoad();
    }

    public void onADExposure(NativeMediaADData nativeMediaADData) {
    }

    public void onADClicked(NativeMediaADData nativeMediaADData) {
    }

    public void onADStatusChanged(NativeMediaADData nativeMediaADData) {
    }

    public void onADError(NativeMediaADData nativeMediaADData, AdError adError) {
    }

    public void onADVideoLoaded(NativeMediaADData nativeMediaADData) {
    }
}
