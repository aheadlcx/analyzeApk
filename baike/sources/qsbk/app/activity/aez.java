package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

class aez implements SimpleCallBack {
    final /* synthetic */ Article a;
    final /* synthetic */ VideoImmersionActivity b;

    aez(VideoImmersionActivity videoImmersionActivity, Article article) {
        this.b = videoImmersionActivity;
        this.a = article;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "匿名糗事成功！", Integer.valueOf(0)).show();
        int indexOf = this.b.o.indexOf(this.a);
        this.a.login = "";
        this.a.anonymous = true;
        this.b.o.set(indexOf, this.a);
        this.b.q.notifyDataSetChanged();
        this.b.checkToPlay();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
