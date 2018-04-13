package qsbk.app.slide;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Comment;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class ag implements SimpleCallBack {
    final /* synthetic */ Comment a;
    final /* synthetic */ SingleArticleFragment b;

    ag(SingleArticleFragment singleArticleFragment, Comment comment) {
        this.b = singleArticleFragment;
        this.a = comment;
    }

    public void onSuccess(JSONObject jSONObject) {
        a();
    }

    public void onFailure(int i, String str) {
        if (i == 40002) {
            a();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("删除失败，请稍后再试。 %s", new Object[]{str})).show();
    }

    void a() {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "删除成功！", Integer.valueOf(0)).show();
        SharePreferenceUtils.remove(this.b.y());
        this.b.n.a.remove(this.a);
        this.b.o.a.remove(this.a);
        this.b.p.a.remove(this.a);
        if (this.b.n.a.size() == 0) {
            this.b.q.addFooterView(this.b.M);
        }
        this.b.m.notifyDataSetChanged();
    }
}
