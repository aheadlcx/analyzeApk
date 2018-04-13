package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpUtils;

final class i implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ CircleArticle b;

    i(Context context, CircleArticle circleArticle) {
        this.a = context;
        this.b = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (HttpUtils.isNetworkConnected(this.a)) {
            ArticleMoreOperationbar.a(this.a, this.b);
        } else {
            Toast.makeText(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), 0).show();
        }
    }
}
