package qsbk.app.widget;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.ToastAndDialog;

final class x implements SimpleCallBack {
    final /* synthetic */ CircleArticle a;

    x(CircleArticle circleArticle) {
        this.a = circleArticle;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "举报成功，感谢您的反馈", Integer.valueOf(0)).show();
        CircleArticleBus.deleteArticle(this.a, null);
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "已举报", Integer.valueOf(0)).show();
    }
}
