package qsbk.app.ad.feedsad.qhad;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class b implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ a b;

    b(a aVar, View view) {
        this.b = aVar;
        this.a = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        FeedsAdStat.onClick(this.b.a.mContext, "qh_native");
        this.b.a.mNativeAd.onAdClick((Activity) this.b.a.mContext, this.a);
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
    }
}
