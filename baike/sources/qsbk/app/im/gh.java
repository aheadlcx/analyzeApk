package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class gh implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ gf b;

    gh(gf gfVar, String[] strArr) {
        this.b = gfVar;
        this.a = strArr;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.a[i];
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            this.b.a.report(this.b.a.m, str);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试", Integer.valueOf(0)).show();
        dialogInterface.dismiss();
    }
}
