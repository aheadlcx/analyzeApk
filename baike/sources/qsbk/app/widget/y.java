package qsbk.app.widget;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.ToastAndDialog;

final class y implements SimpleCallBack {
    final /* synthetic */ CircleArticle a;

    y(CircleArticle circleArticle) {
        this.a = circleArticle;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "操作成功").show();
        CircleArticleBus.deleteArticle(this.a, null);
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
