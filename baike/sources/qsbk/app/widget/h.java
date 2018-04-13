package qsbk.app.widget;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

final class h extends ProgressDialogCallBack {
    final /* synthetic */ CircleArticle a;

    h(Context context, String str, CircleArticle circleArticle) {
        this.a = circleArticle;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已移除", Integer.valueOf(1)).show();
        CircleArticleBus.deleteArticle(this.a, null);
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(1)).show();
    }
}
