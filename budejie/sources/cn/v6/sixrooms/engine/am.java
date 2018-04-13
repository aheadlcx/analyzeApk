package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class am extends VLAsyncHandler<String> {
    final /* synthetic */ PropActionEngine a;

    am(PropActionEngine propActionEngine) {
        this.a = propActionEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                if ("001".equals(string2)) {
                    PropActionEngine.a(this.a).result(true);
                } else {
                    PropActionEngine.a(this.a).handleErrorInfo(string2, string);
                }
            } catch (JSONException e) {
                PropActionEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && PropActionEngine.a(this.a) != null) {
            PropActionEngine.a(this.a).error(1006);
        }
    }
}
