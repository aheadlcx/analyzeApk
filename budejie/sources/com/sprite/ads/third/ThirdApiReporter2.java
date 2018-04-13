package com.sprite.ads.third;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.sprite.ads.internal.a.b;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.a;
import com.sprite.ads.internal.net.c;
import com.sprite.ads.nati.reporter.ConfirmDownloadReporter;
import java.util.List;

public class ThirdApiReporter2 extends ConfirmDownloadReporter {
    Handler mHandler = new Handler(Looper.getMainLooper());
    ThirdApiAdData nativeAdData;

    public ThirdApiReporter2(ThirdApiAdData thirdApiAdData) {
        this.nativeAdData = thirdApiAdData;
    }

    public static void EventReport(List<String> list) {
        ADLog.d("api report2 EventReport");
        if (list != null && list.size() > 0) {
            for (String str : list) {
                ADLog.d("api report2 url=" + str);
                a.a(str, new c());
            }
        }
    }

    public void onClicked(final View view) {
        ADLog.d("api report2 onClicked");
        EventReport(this.nativeAdData.getClickTrackingUrl());
        if (this.nativeAdData.getActionType() == 2) {
            final AdItem newAdItem = this.nativeAdData.getNewAdItem();
            this.mHandler.post(new Runnable() {
                public void run() {
                    com.sprite.ads.internal.a.c.a().a((Activity) view.getContext());
                    com.sprite.ads.internal.a.c.a().b(null, newAdItem, ThirdApiReporter2.this.downwarn);
                }
            });
            return;
        }
        List clickThroughUrl = this.nativeAdData.getClickThroughUrl();
        if (clickThroughUrl == null || clickThroughUrl.size() <= 0) {
            ADLog.d("api report2 click URL为空");
            return;
        }
        ADLog.d("api report2 click URL:" + ((String) clickThroughUrl.get(0)));
        b bVar = new b();
        AdItem selfItem = new SelfItem();
        selfItem.setUrl((String) clickThroughUrl.get(0));
        bVar.b(view.getContext(), selfItem);
    }

    public void onExposured(View view) {
        ADLog.d("api report2 onExposured");
        EventReport(this.nativeAdData.getReportTrackingUrl());
    }

    public void onPlay(View view) {
    }
}
