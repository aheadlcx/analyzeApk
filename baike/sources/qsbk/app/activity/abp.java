package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ToastAndDialog;

class abp implements HttpCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ SearchGroupActivity c;

    abp(SearchGroupActivity searchGroupActivity, boolean z, int i) {
        this.c = searchGroupActivity;
        this.a = z;
        this.b = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.c.h = null;
        if (this.a) {
            this.c.hideLoading();
        }
        try {
            boolean z = jSONObject.getBoolean("has_more");
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            if (this.b == 1) {
                this.c.b.clear();
                this.c.i.refreshDone();
            } else {
                this.c.i.loadMoreDone(true);
            }
            this.c.l.setVisibility(8);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.c.b.add(new GroupBriefInfo(jSONArray.getJSONObject(i)));
            }
            this.c.g = this.b + 1;
            if (z) {
                this.c.i.setLoadMoreEnable(true);
            } else {
                this.c.i.setLoadMoreEnable(false);
            }
            this.c.e.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        this.c.h = null;
        this.c.l.setVisibility(8);
        if (this.b == 1) {
            this.c.i.refreshDone();
        } else {
            this.c.i.loadMoreDone(false);
        }
        if (this.a) {
            this.c.hideLoading();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
    }
}
