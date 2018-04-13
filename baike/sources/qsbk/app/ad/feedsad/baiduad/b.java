package qsbk.app.ad.feedsad.baiduad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class b implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ a b;

    b(a aVar, View view) {
        this.b = aVar;
        this.a = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.mBaiduNativeResponse.handleClick(this.a);
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUSHILIST);
    }
}
