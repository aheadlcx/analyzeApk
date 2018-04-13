package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class e implements HttpCallBack {
    final /* synthetic */ BaseLiveTabFragment a;

    e(BaseLiveTabFragment baseLiveTabFragment) {
        this.a = baseLiveTabFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.d();
        try {
            this.a.fillData(jSONObject);
            if (this.a.b.size() == 0 && this.a.n.size() == 0) {
                this.a.i.set(UIHelper.getEmptyImg(), this.a.getDataEmptyTip());
                this.a.i.show();
                this.a.f = null;
            }
            this.a.i.hide();
            this.a.f = null;
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
    }

    public void onFailure(String str, String str2) {
        this.a.d();
        if (this.a.a == 1) {
            this.a.c.refreshDone();
            if (this.a.b.size() == 0) {
                this.a.i.set(UIHelper.getFailImg(), str2);
                this.a.i.show();
            } else {
                this.a.i.hide();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
            }
        } else {
            this.a.i.hide();
            this.a.c.loadMoreDone(false);
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
        this.a.f = null;
    }
}
