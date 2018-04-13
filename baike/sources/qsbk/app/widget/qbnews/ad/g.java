package qsbk.app.widget.qbnews.ad;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class g implements OnClickListener {
    final /* synthetic */ QhNewsAdCell a;

    g(QhNewsAdCell qhNewsAdCell) {
        this.a = qhNewsAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        FeedsAdStat.onClick(QsbkApp.mContext, "qh_native");
        this.a.i.onAdClick((Activity) QsbkApp.mContext, this.a.getCellView());
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.QH, AD_TYPE.QIUWEN);
    }
}
