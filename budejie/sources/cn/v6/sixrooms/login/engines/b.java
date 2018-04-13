package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class b extends VLAsyncHandler<String> {
    final /* synthetic */ GtAuthEngine a;

    b(GtAuthEngine gtAuthEngine) {
        this.a = gtAuthEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z || !CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = new JSONObject(string2);
                    this.a.a.success(jSONObject2.getInt("success"), jSONObject2.getString("gt"), jSONObject2.getString("challenge"));
                    return;
                } else if ("106".equals(string)) {
                    this.a.a.success(0, "", "");
                    return;
                } else {
                    this.a.a.serverError(string, string2);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.a.a.phoneError(1007);
                return;
            }
        }
        this.a.a.phoneError(1006);
    }
}
