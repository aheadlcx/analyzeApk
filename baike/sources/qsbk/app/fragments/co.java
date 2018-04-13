package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.cache.FileCache;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class co implements HttpCallBack {
    final /* synthetic */ FollowCircleFragment a;

    co(FollowCircleFragment followCircleFragment) {
        this.a = followCircleFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.d();
        try {
            this.a.a(jSONObject);
            if (this.a.e.size() == 0) {
                this.a.i.set(UIHelper.getEmptyImg(), "暂时没有动态，稍后刷新试试");
                this.a.i.show();
            } else {
                this.a.i.hide();
            }
            if (this.a.d == 1) {
                ReadCircle.setFirstArticleRead(this.a.e);
                FileCache.cacheTextToDisk(QsbkApp.getInstance().getApplicationContext(), FollowCircleFragment.b, jSONObject.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        this.a.c = null;
        this.a.d = this.a.d + 1;
    }

    public void onFailure(String str, String str2) {
        this.a.d();
        if (this.a.d == 1) {
            this.a.f.refreshDone();
            if (this.a.e.size() == 0) {
                this.a.i.set(UIHelper.getFailImg(), str2);
                this.a.i.show();
            } else {
                this.a.i.hide();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
            }
        } else {
            this.a.i.hide();
            this.a.f.loadMoreDone(false);
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
        this.a.c = null;
    }
}
