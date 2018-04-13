package qsbk.app.widget.qbnews.ad;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

public class QbNewsAdCell extends BaseNewsAdCell {
    public QbNewsAdCell(NewsAdSign newsAdSign) {
        a(newsAdSign);
    }

    public void onUpdate() {
        QbAdItem qbAdItem = (QbAdItem) getItem();
        this.e.setText(qbAdItem.description);
        displayImage(this.f, qbAdItem.banner, this.s);
        this.g.setText("糗事百科");
    }

    public void onClick() {
        super.onClick();
        QbAdItem qbAdItem = (QbAdItem) getItem();
        View cellView = getCellView();
        if (!QbAdItem.QB_APP.equals(qbAdItem.action_type)) {
            qbAdItem.onAppClick(cellView);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUWEN);
        } else if (qbAdItem.getApkStatus(qbAdItem.getUrlAndPackageNameFromArsg(qbAdItem.action_args)) == 1) {
            Context context = cellView.getContext();
            String network = HttpUtils.getNetwork(context);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new f(this)).setPositiveButton("确认", new e(this, qbAdItem, cellView)).create().show();
                return;
            }
            qbAdItem.onAppClick(cellView);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUWEN);
        } else {
            qbAdItem.onAppClick(cellView);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUWEN);
        }
    }
}
