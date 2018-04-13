package qsbk.app.utils;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.GroupMemberManager.CallBack;

class ag implements SimpleCallBack {
    final /* synthetic */ CallBack a;
    final /* synthetic */ GroupMemberManager b;

    ag(GroupMemberManager groupMemberManager, CallBack callBack) {
        this.b = groupMemberManager;
        this.a = callBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int optInt;
            ArrayList a = this.b.a(jSONObject);
            this.b.saveMemberToCache(a);
            if (jSONObject.has("at_all_left")) {
                optInt = jSONObject.optInt("at_all_left");
            } else {
                optInt = -1;
            }
            this.a.onSuccess(a, optInt);
        } catch (JSONException e) {
            e.printStackTrace();
            this.a.onFailure(-1, "数据加载失败");
        }
    }

    public void onFailure(int i, String str) {
        this.a.onFailure(i, str);
    }
}
