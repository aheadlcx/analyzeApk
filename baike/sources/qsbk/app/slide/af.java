package qsbk.app.slide;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class af implements SimpleCallBack {
    final /* synthetic */ SingleArticleFragment a;

    af(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "封禁成功！", Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
