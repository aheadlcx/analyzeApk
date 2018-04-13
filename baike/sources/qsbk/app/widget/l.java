package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

final class l implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ CircleArticle b;

    l(String[] strArr, CircleArticle circleArticle) {
        this.a = strArr;
        this.b = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = this.a[i];
        if (HttpUtils.netIsAvailable()) {
            dialogInterface.dismiss();
            ArticleMoreOperationbar.a(this.b, str);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试", Integer.valueOf(0)).show();
        dialogInterface.dismiss();
    }
}
