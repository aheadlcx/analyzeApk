package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.image.issue.Logger;

class ady implements HttpCallBack {
    final /* synthetic */ UnSubscribeListActivity a;

    ady(UnSubscribeListActivity unSubscribeListActivity) {
        this.a = unSubscribeListActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if ((Constants.UNSUBSCRIBE_TA + "List").equalsIgnoreCase(str)) {
            Logger.getInstance().debug(UnSubscribeListActivity.h, "unSubList", jSONObject.toString());
            if (1 == this.a.f) {
                this.a.e.clear();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("items");
            this.a.g = this.a.e.size() + optJSONArray.length() < jSONObject.optInt("total");
            if (this.a.i != null) {
                if (optJSONArray.length() == 0) {
                    this.a.i.setVisibility(0);
                } else {
                    this.a.i.setVisibility(8);
                }
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.parseBaseInfo(optJSONArray.optJSONObject(i));
                this.a.e.add(baseUserInfo);
            }
            UnSubscribeListActivity unSubscribeListActivity = this.a;
            unSubscribeListActivity.f++;
            this.a.d.notifyDataSetChanged();
            this.a.b.refreshDone();
            if (this.a.e.size() > 0) {
                this.a.b.loadMoreDone(true);
            }
            if (this.a.g) {
                this.a.b.setLoadMoreEnable(true);
            } else {
                this.a.b.setLoadMoreEnable(false);
            }
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
