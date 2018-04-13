package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.qq.e.ads.nativ.NativeMediaADData;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;

class c implements OnClickListener {
    final /* synthetic */ NativeMediaADData a;
    final /* synthetic */ View b;
    final /* synthetic */ Context c;
    final /* synthetic */ GDTAdCell d;

    c(GDTAdCell gDTAdCell, NativeMediaADData nativeMediaADData, View view, Context context) {
        this.d = gDTAdCell;
        this.a = nativeMediaADData;
        this.b = view;
        this.c = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onClicked(this.b);
        FeedsAdStat.onClick(this.c, "gdt_native");
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUYOUQUANLIST);
    }
}
