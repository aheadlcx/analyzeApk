package qsbk.app.slide;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class o implements SimpleCallBack {
    final /* synthetic */ SingleArticleFragment a;

    o(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        a();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "评论成功！", Integer.valueOf(0)).show();
        this.a.a();
        this.a.P = jSONObject;
        this.a.s();
        this.a.Q = null;
    }

    public void onFailure(int i, String str) {
        a();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }

    void a() {
        this.a.aE = null;
        if (this.a.ab && !this.a.isDetached() && !this.a.isRemoving()) {
            this.a.u.setClickable(true);
        }
    }
}
