package qsbk.app.widget.qbnews.ad;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import com.ak.android.engine.nav.NativeAd;
import com.ak.android.engine.navbase.NativeAdListener;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

public class QhNewsAdCell extends BaseNewsAdCell implements NativeAdListener {
    private NativeAd i;
    private String j;
    private String k;

    public QhNewsAdCell(NewsAdSign newsAdSign) {
        a(newsAdSign);
    }

    public void onUpdate() {
        QhAdItemData qhAdItemData = (QhAdItemData) getItem();
        this.i = qhAdItemData.getAdView().getmNativeAd();
        c();
        this.e.setText(this.j);
        displayImage(this.f, this.k, this.s);
        this.g.setText("360广告");
        FeedsAd.getInstance().setAdShowed(qhAdItemData, AD_PROVIDER.QH);
    }

    private void c() {
        this.i.setAdListener(this);
        JSONObject content = this.i.getContent();
        this.j = content.optString(SocialConstants.PARAM_APP_DESC);
        this.k = content.optString("contentimg");
    }

    public void onClick() {
        super.onClick();
        if (NetworkUtils.getInstance().isWifiAvailable()) {
            this.i.onAdClick((Activity) getCellView().getContext(), getCellView());
            FeedsAdStat.onClick(QsbkApp.mContext, "qh_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.QH, AD_TYPE.QIUWEN);
            return;
        }
        new Builder(QsbkApp.mContext).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + HttpUtils.getNetwork(QsbkApp.mContext) + "网络，开始下载应用？").setNegativeButton("取消", new h(this)).setPositiveButton("确认", new g(this)).create().show();
    }

    public void onLandingPageChange(int i) {
    }

    public void onAlertChange(int i) {
    }

    public void onLeftApplication() {
    }
}
