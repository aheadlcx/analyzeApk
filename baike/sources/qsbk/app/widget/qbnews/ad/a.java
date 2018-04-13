package qsbk.app.widget.qbnews.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.baidu.mobad.feeds.NativeResponse;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

class a implements OnClickListener {
    final /* synthetic */ NativeResponse a;
    final /* synthetic */ View b;
    final /* synthetic */ BaiduNewsAdCell c;

    a(BaiduNewsAdCell baiduNewsAdCell, NativeResponse nativeResponse, View view) {
        this.c = baiduNewsAdCell;
        this.a = nativeResponse;
        this.b = view;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.handleClick(this.b);
        dialogInterface.dismiss();
        ReportAdForMedalUtil.report(AD_PROVIDER.BAIDU, AD_TYPE.QIUWEN);
    }
}
