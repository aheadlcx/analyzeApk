package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

final class j implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ CircleArticle b;

    j(Context context, CircleArticle circleArticle) {
        this.a = context;
        this.b = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            if (HttpUtils.isNetworkConnected(this.a)) {
                ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在发送请求，请稍候...", Integer.valueOf(0)).show();
                ArticleMoreOperationbar.forbid(this.a, this.b);
                return;
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        } else if (i == 1) {
            dialogInterface.dismiss();
        }
    }
}
