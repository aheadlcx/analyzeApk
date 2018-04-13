package qsbk.app.ad.feedsad.baiduad;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.ToastAndDialog;

class a implements OnClickListener {
    final /* synthetic */ BaiduAdView a;

    a(BaiduAdView baiduAdView) {
        this.a = baiduAdView;
    }

    public void onClick(View view) {
        DebugUtil.debug("BaiduAd", "mAdView click, isDownload=" + this.a.mBaiduNativeResponse.isDownloadApp() + ",isWifi=" + HttpUtils.isWifi(this.a.mContext));
        if (this.a.mBaiduNativeResponse.isDownloadApp()) {
            String network = HttpUtils.getNetwork(this.a.mContext);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(this.a.mContext).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new c(this)).setPositiveButton("确认", new b(this, view)).create().show();
            } else {
                Object optString = QsbkApp.indexConfig.optString("ad_click_toast");
                if (!TextUtils.isEmpty(optString)) {
                    ToastAndDialog.makeNeutralToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                }
                this.a.mBaiduNativeResponse.handleClick(view);
                ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUSHILIST);
            }
        } else {
            this.a.mBaiduNativeResponse.handleClick(view);
            ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUSHILIST);
        }
        FeedsAdStat.onClick(view.getContext(), "baidu");
    }
}
