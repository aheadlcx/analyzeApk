package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class o extends VLAsyncHandler<String> {
    final /* synthetic */ CrashErrorInfoEngine a;

    o(CrashErrorInfoEngine crashErrorInfoEngine) {
        this.a = crashErrorInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                if ("001".equals(string2)) {
                    CrashErrorInfoEngine.a(this.a).result(true);
                } else {
                    CrashErrorInfoEngine.a(this.a).handleErrorInfo(string2, string);
                }
            } catch (JSONException e) {
                CrashErrorInfoEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && CrashErrorInfoEngine.a(this.a) != null) {
            CrashErrorInfoEngine.a(this.a).error(1006);
        }
    }
}
