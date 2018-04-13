package qsbk.app.widget.qbnews.ad;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.baidu.mobad.feeds.NativeResponse;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.ToastAndDialog;

public class BaiduNewsAdCell extends BaseNewsAdCell {
    public BaiduNewsAdCell(NewsAdSign newsAdSign) {
        a(newsAdSign);
    }

    public void onUpdate() {
        NativeResponse ref = ((BaiduAdItemData) getItem()).getView().getRef();
        this.e.setText(ref.getDesc());
        displayImage(this.f, ref.getImageUrl(), this.s);
        this.g.setText("百度");
        ref.recordImpression(this.h);
    }

    public void onClick() {
        super.onClick();
        NativeResponse ref = ((BaiduAdItemData) getItem()).getView().getRef();
        View cellView = getCellView();
        Context context = cellView.getContext();
        if (ref.isDownloadApp()) {
            String network = HttpUtils.getNetwork(context);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new b(this)).setPositiveButton("确认", new a(this, ref, cellView)).create().show();
            } else {
                Object optString = QsbkApp.indexConfig.optString("ad_click_toast");
                if (!TextUtils.isEmpty(optString)) {
                    ToastAndDialog.makeNeutralToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                }
                ref.handleClick(cellView);
                ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUWEN);
            }
        } else {
            ref.handleClick(cellView);
            ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUWEN);
        }
        FeedsAdStat.onClick(cellView.getContext(), "baidu");
    }
}
