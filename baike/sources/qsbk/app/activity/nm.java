package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.ChatMsg;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.ToastAndDialog;

class nm implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ GroupNoticeActivity b;

    nm(GroupNoticeActivity groupNoticeActivity, int i) {
        this.b = groupNoticeActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        int i = 0;
        this.b.h = null;
        this.b.hideLoading();
        try {
            int i2 = (jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more") != 0) ? 1 : 0;
            if (this.a == 1) {
                this.b.g.clear();
                this.b.e.refreshDone();
            } else {
                this.b.e.loadMoreDone(true);
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            while (i < jSONArray.length()) {
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.parseFromJSONObject(jSONArray.getJSONObject(i));
                GroupNotice groupNotice = chatMsg.getGroupNotice();
                if (groupNotice != null) {
                    this.b.g.add(groupNotice);
                }
                i++;
            }
            this.b.a = this.a;
            if (i2 != 0) {
                this.b.e.setLoadMoreEnable(true);
            } else {
                this.b.e.setLoadMoreEnable(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.b.d.notifyDataSetChanged();
    }

    public void onFailure(int i, String str) {
        this.b.h = null;
        this.b.hideLoading();
        this.b.e.refreshDone();
        this.b.e.loadMoreDone(false);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
