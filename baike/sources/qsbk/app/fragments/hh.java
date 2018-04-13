package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.cache.FileCache;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class hh implements HttpCallBack {
    final /* synthetic */ NearbyCircleFragment a;

    hh(NearbyCircleFragment nearbyCircleFragment) {
        this.a = nearbyCircleFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.c();
        try {
            this.a.a(jSONObject);
            if (this.a.e.size() == 0) {
                this.a.q.set(UIHelper.getEmptyImg(), "暂时没有动态，稍后刷新试试");
                this.a.q.show();
            } else {
                this.a.q.hide();
            }
            if (this.a.d - 1 == 1) {
                ReadCircle.setFirstArticleRead(this.a.e);
                FileCache.cacheTextToDisk(QsbkApp.getInstance().getApplicationContext(), NearbyCircleFragment.a, jSONObject.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        this.a.c = null;
    }

    public void onFailure(String str, String str2) {
        this.a.c();
        if (this.a.d == 1) {
            this.a.g.refreshDone();
            if (this.a.e.size() == 0) {
                this.a.q.set(UIHelper.getFailImg(), str2);
                this.a.q.show();
            } else {
                this.a.q.hide();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
            }
        } else {
            this.a.q.hide();
            this.a.g.loadMoreDone(false);
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
        this.a.c = null;
    }
}
