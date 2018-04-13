package qsbk.app.utils;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

final class ah implements SimpleCallBack {
    int a = 0;

    ah() {
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            boolean optBoolean = jSONObject.optBoolean("has_more");
            GroupMsgUtils.b(jSONObject);
            if (optBoolean) {
                int i = this.a + 1;
                this.a = i;
                GroupMsgUtils.b(i, this);
                return;
            }
            GroupMsgUtils.b(true, 0, null);
        } catch (Exception e) {
            onFailure(0, "数据出错");
        }
    }

    public void onFailure(int i, String str) {
        if (this.a == 0) {
            GroupMsgUtils.b(false, i, str);
        } else {
            GroupMsgUtils.b(true, 0, null);
        }
    }
}
