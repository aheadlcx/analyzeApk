package qsbk.app.widget.qiuyoucircle;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.ak.android.engine.nav.NativeAd;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QHAdCell;

class f implements OnClickListener {
    final /* synthetic */ NativeAd a;
    final /* synthetic */ Context b;
    final /* synthetic */ View c;
    final /* synthetic */ QHAdCell d;

    f(QHAdCell qHAdCell, NativeAd nativeAd, Context context, View view) {
        this.d = qHAdCell;
        this.a = nativeAd;
        this.b = context;
        this.c = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onAdClick((Activity) this.b, this.c);
        FeedsAdStat.onClick(this.b, "qh_native");
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.QH, AD_TYPE.QIUSHILIST);
    }
}
