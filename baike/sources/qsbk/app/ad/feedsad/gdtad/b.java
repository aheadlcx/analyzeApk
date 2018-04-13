package qsbk.app.ad.feedsad.gdtad;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.ToastAndDialog;

class b implements OnClickListener {
    final /* synthetic */ AdView a;
    final /* synthetic */ GdtAdView b;

    b(GdtAdView gdtAdView, AdView adView) {
        this.b = gdtAdView;
        this.a = adView;
    }

    public void onClick(View view) {
        int aPPStatus = this.b.ref.getAPPStatus();
        if (aPPStatus == 1 || aPPStatus == 4 || aPPStatus == 8) {
            this.b.ref.onClicked(this.a);
            FeedsAdStat.onClick(view.getContext(), "gdt_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
            return;
        }
        Context context = view.getContext();
        String network = HttpUtils.getNetwork(context);
        if (FeedsAdUtils.needShowConfirm(network)) {
            new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new d(this)).setPositiveButton("确认", new c(this, context)).create().show();
            return;
        }
        Object optString = QsbkApp.indexConfig.optString("ad_click_toast");
        if (!TextUtils.isEmpty(optString)) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
        }
        this.b.ref.onClicked(this.a);
        FeedsAdStat.onClick(view.getContext(), "gdt_native");
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
    }
}
