package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

final class ab implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ CircleArticle b;

    ab(Context context, CircleArticle circleArticle) {
        this.a = context;
        this.b = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (HttpUtils.isNetworkConnected(this.a)) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在处理，请稍候...", Integer.valueOf(0)).show();
            ArticleMoreOperationbar.deleteArticle(this.b);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
    }
}
