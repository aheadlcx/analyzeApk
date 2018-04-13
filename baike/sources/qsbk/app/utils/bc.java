package qsbk.app.utils;

import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

final class bc implements SimpleCallBack {
    int a = 0;
    final /* synthetic */ SimpleCallBack b;

    bc(SimpleCallBack simpleCallBack) {
        this.b = simpleCallBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            boolean optBoolean = jSONObject.optBoolean("has_more");
            JSONObject jSONObject2 = jSONObject.getJSONObject("sws_dict");
            Iterator keys = jSONObject2.keys();
            Editor edit = TemporaryNoteUtils.getPreferences().edit();
            edit.putBoolean("loaded", true);
            while (keys.hasNext()) {
                String str = (String) keys.next();
                edit.putBoolean(str, !"0".equals(jSONObject2.getString(str)));
            }
            edit.apply();
            if (optBoolean) {
                int i = this.a + 1;
                this.a = i;
                TemporaryNoteUtils.loadSwitch(i, this);
                return;
            }
            this.b.onSuccess(null);
        } catch (Exception e) {
            onFailure(0, "数据出错");
        }
    }

    public void onFailure(int i, String str) {
        if (this.a == 0) {
            this.b.onFailure(i, str);
        } else {
            this.b.onSuccess(null);
        }
    }
}
