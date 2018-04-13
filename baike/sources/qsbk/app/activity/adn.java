package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.datastore.DatabaseHelper;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class adn implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ TopicBlackListActivity b;

    adn(TopicBlackListActivity topicBlackListActivity, int i) {
        this.b = topicBlackListActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        int i = 0;
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more") != 0;
        if (this.a == 1) {
            this.b.hideLoading();
            this.b.f.clear();
            this.b.b.refreshDone();
        } else {
            this.b.b.loadMoreDone(true);
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(DatabaseHelper.TABLE_USERS);
            while (i < jSONArray.length()) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.parseBaseInfo(jSONArray.getJSONObject(i));
                this.b.f.add(baseUserInfo);
                i++;
            }
            this.b.a = this.a;
            this.b.b.setLoadMoreEnable(z);
            this.b.d.notifyDataSetChanged();
            this.b.supportInvalidateOptionsMenu();
            if (this.b.f.size() == 0) {
                this.b.g.set(UIHelper.getEmptyImg(), "没有屏蔽用户哦");
                this.b.g.show();
                return;
            }
            this.b.g.hide();
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        if (this.b.a == 1) {
            this.b.hideLoading();
            this.b.b.refreshDone();
            if (this.b.f.size() == 0) {
                this.b.g.set(UIHelper.getFailImg(), str);
                this.b.g.show();
                return;
            }
            this.b.g.hide();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
            return;
        }
        this.b.g.hide();
        this.b.b.loadMoreDone(false);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
