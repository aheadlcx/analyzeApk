package qsbk.app.ad.feedsad.gdtad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class c implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ b b;

    c(b bVar, Context context) {
        this.b = bVar;
        this.a = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.b.ref.onClicked(this.b.a);
        FeedsAdStat.onClick(this.a, "gdt_native");
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
    }
}
