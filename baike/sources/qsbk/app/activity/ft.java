package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class ft implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ CircleComment b;
    final /* synthetic */ CircleArticleActivity c;

    ft(CircleArticleActivity circleArticleActivity, String[] strArr, CircleComment circleComment) {
        this.c = circleArticleActivity;
        this.a = strArr;
        this.b = circleComment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.a[i];
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            this.c.reportComment(this.b, str);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试").show();
        dialogInterface.dismiss();
    }
}
