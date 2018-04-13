package qsbk.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class dv implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ du b;

    dv(du duVar, Context context) {
        this.b = duVar;
        this.a = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.v.onClicked(this.b.a.getCellView());
        FeedsAdStat.onClick(this.a, "gdt_native");
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUYOUQUANLIST);
    }
}
