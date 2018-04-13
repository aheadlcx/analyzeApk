package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class ie implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ id b;

    ie(id idVar, int i) {
        this.b = idVar;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.b.a[this.a];
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            this.b.b.c(str);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.b.b, "未发现可用网络，请稍候再试").show();
        dialogInterface.dismiss();
    }
}
