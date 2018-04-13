package qsbk.app.widget.qbnews.ad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.qq.e.ads.nativ.NativeMediaADData;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class c implements OnClickListener {
    final /* synthetic */ NativeMediaADData a;
    final /* synthetic */ View b;
    final /* synthetic */ Context c;
    final /* synthetic */ GdtNewsAdCell d;

    c(GdtNewsAdCell gdtNewsAdCell, NativeMediaADData nativeMediaADData, View view, Context context) {
        this.d = gdtNewsAdCell;
        this.a = nativeMediaADData;
        this.b = view;
        this.c = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onClicked(this.b);
        FeedsAdStat.onClick(this.c, "gdt_native");
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUWEN);
    }
}
