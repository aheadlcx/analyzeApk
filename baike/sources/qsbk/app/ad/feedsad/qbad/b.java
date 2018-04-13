package qsbk.app.ad.feedsad.qbad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class b implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ QbAdApkDownloader d;

    b(QbAdApkDownloader qbAdApkDownloader, String str, Context context, String str2) {
        this.d = qbAdApkDownloader;
        this.a = str;
        this.b = context;
        this.c = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已经开始下载 " + this.a, Integer.valueOf(0)).show();
        this.d.downloadFileImpl(this.b, this.c, this.a);
        dialogInterface.dismiss();
    }
}
