package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QbAdCell;

class h implements OnClickListener {
    final /* synthetic */ QbAdItem a;
    final /* synthetic */ View b;
    final /* synthetic */ QbAdCell c;

    h(QbAdCell qbAdCell, QbAdItem qbAdItem, View view) {
        this.c = qbAdCell;
        this.a = qbAdItem;
        this.b = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onAppClick(this.b);
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.QIUYOUQUANLIST);
    }
}
