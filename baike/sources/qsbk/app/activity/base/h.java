package qsbk.app.activity.base;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

class h implements SimpleCallBack {
    final /* synthetic */ Article a;
    final /* synthetic */ BaseArticleListViewFragment b;

    h(BaseArticleListViewFragment baseArticleListViewFragment, Article article) {
        this.b = baseArticleListViewFragment;
        this.a = article;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "删除糗事成功！", Integer.valueOf(0)).show();
        this.b.j.remove(this.a);
        this.b.i.notifyDataSetChanged();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
