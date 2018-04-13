package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class r implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ q b;

    r(q qVar, int i) {
        this.b = qVar;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.b.b[this.a];
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            ArticleMoreOperationbar.reportTopic(this.b.a, str, this.b.c);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.b.a, "未发现可用网络，请稍候再试").show();
        dialogInterface.dismiss();
    }
}
