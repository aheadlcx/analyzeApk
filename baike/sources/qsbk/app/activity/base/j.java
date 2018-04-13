package qsbk.app.activity.base;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

class j implements SimpleCallBack {
    final /* synthetic */ Article a;
    final /* synthetic */ BaseArticleListViewFragment b;

    j(BaseArticleListViewFragment baseArticleListViewFragment, Article article) {
        this.b = baseArticleListViewFragment;
        this.a = article;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "匿名糗事成功！", Integer.valueOf(0)).show();
        int indexOf = this.b.j.indexOf(this.a);
        this.a.login = "";
        this.a.anonymous = true;
        this.b.j.set(indexOf, this.a);
        this.b.i.notifyDataSetChanged();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
