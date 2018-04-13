package qsbk.app.ad.feedsad.qhad;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class a implements OnClickListener {
    final /* synthetic */ QhAdView a;

    a(QhAdView qhAdView) {
        this.a = qhAdView;
    }

    public void onClick(View view) {
        if (NetworkUtils.getInstance().isWifiAvailable()) {
            this.a.mNativeAd.onAdClick((Activity) this.a.mContext, view);
            FeedsAdStat.onClick(this.a.mContext, "qh_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.QH, AD_TYPE.QIUSHILIST);
            return;
        }
        new Builder(this.a.mContext).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + HttpUtils.getNetwork(this.a.mContext) + "网络，开始下载应用？").setNegativeButton("取消", new c(this)).setPositiveButton("确认", new b(this, view)).create().show();
    }
}
