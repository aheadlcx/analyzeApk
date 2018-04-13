package qsbk.app.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.LogUtil;

class c implements HttpCallBack {
    final /* synthetic */ JoinedGroupInfoService a;

    c(JoinedGroupInfoService joinedGroupInfoService) {
        this.a = joinedGroupInfoService;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            this.a.d = jSONObject.getInt("total");
            this.a.e = jSONObject.getBoolean("has_more");
            if (this.a.b == 1) {
                this.a.a.clear();
            }
            LogUtil.d("data====" + jSONArray);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.a.a.add(new GroupInfo(optJSONObject));
                }
            }
            this.a.b();
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        this.a.c = null;
    }

    public void onFailure(String str, String str2) {
        LogUtil.d(str2);
        this.a.c = null;
    }
}
