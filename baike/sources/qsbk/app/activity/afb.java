package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Article;
import qsbk.app.utils.ToastAndDialog;

class afb implements SimpleCallBack {
    final /* synthetic */ Article a;
    final /* synthetic */ VideoImmersionActivity b;

    afb(VideoImmersionActivity videoImmersionActivity, Article article) {
        this.b = videoImmersionActivity;
        this.a = article;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "封禁糗事成功！", Integer.valueOf(0)).show();
        int i = 0;
        while (i < this.b.o.size()) {
            if (this.b.o.get(i).equals(this.a)) {
                this.b.o.remove(this.a);
                break;
            }
            i++;
        }
        i = 0;
        this.b.q.notifyDataSetChanged();
        this.b.b(i);
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
