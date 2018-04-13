package qsbk.app.activity;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class py implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ InviteFriendActivity b;

    py(InviteFriendActivity inviteFriendActivity, int i) {
        this.b = inviteFriendActivity;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        boolean z = true;
        try {
            int i;
            if (this.a == 1) {
                this.b.f.clear();
            }
            boolean optBoolean = jSONObject.optBoolean("has_more");
            if (jSONObject.optInt("has_more") != 0) {
                i = 1;
            } else {
                i = 0;
            }
            int i2 = optBoolean | i;
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (i = 0; i < jSONArray.length(); i++) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.parseBaseInfo(jSONArray.getJSONObject(i));
                this.b.f.add(baseUserInfo);
            }
            if (this.b.f.size() == 0) {
                this.b.n.set(UIHelper.getEmptyImg(), "这里什么也没有");
                this.b.n.show();
            } else {
                this.b.n.hide();
            }
            this.b.a = this.a + 1;
            this.b.b(this.b.l == null ? "" : this.b.l.getText().toString());
            this.b.d.notifyDataSetChanged();
            this.b.b.refreshDone();
            if (this.b.f.size() > 0) {
                this.b.b.loadMoreDone(true);
            }
            InviteFriendActivity inviteFriendActivity = this.b;
            if (i2 != 0) {
                z = false;
            }
            inviteFriendActivity.o = z;
            if (i2 == 0) {
                this.b.b.setLoadMoreEnable(false);
                return;
            }
            this.b.b.setLoadMoreEnable(true);
            if (this.b.e.size() < 10) {
                this.b.b.loadMore();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        if (this.a == 1) {
            this.b.b.refreshDone();
            if (this.b.f.size() == 0) {
                this.b.n.set(UIHelper.getFailImg(), str2);
                this.b.n.show();
            } else {
                this.b.n.hide();
            }
        } else {
            this.b.b.loadMoreDone(false);
            this.b.n.hide();
        }
        if (!TextUtils.isEmpty(str2)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
    }
}
