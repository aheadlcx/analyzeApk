package qsbk.app.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ToastAndDialog;

class hb implements HttpCallBack {
    final /* synthetic */ NearByGroupFragment a;

    hb(NearByGroupFragment nearByGroupFragment) {
        this.a = nearByGroupFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.c();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            this.a.g = jSONObject.optInt("total");
            boolean optBoolean = jSONObject.optBoolean("has_more");
            if (this.a.f == 1) {
                this.a.c.clear();
                this.a.a.refreshDone();
            } else {
                this.a.a.loadMoreDone(true);
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.a.c.add(new GroupBriefInfo(optJSONObject));
                }
            }
            this.a.f = this.a.f + 1;
            this.a.d.notifyDataSetChanged();
            if (optBoolean) {
                this.a.a.setLoadMoreEnable(true);
            } else {
                this.a.a.setLoadMoreEnable(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        if (this.a.c.size() == 0) {
            this.a.e();
        }
        this.a.e = null;
    }

    public void onFailure(String str, String str2) {
        this.a.c();
        if (this.a.f == 1) {
            this.a.a.refreshDone();
        } else {
            this.a.a.loadMoreDone(false);
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.e = null;
    }
}
