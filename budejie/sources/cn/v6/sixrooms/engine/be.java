package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class be extends VLAsyncHandler<String> {
    final /* synthetic */ SetUserVisibleEngine a;

    be(SetUserVisibleEngine setUserVisibleEngine) {
        this.a = setUserVisibleEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if (string.equals("001")) {
                    SetUserVisibleEngine.a(this.a).result(jSONObject.getString("content"));
                } else {
                    SetUserVisibleEngine.a(this.a).handleErrorInfo(string, jSONObject.getString("content"));
                }
            } catch (JSONException e) {
                SetUserVisibleEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && SetUserVisibleEngine.a(this.a) != null) {
            SetUserVisibleEngine.a(this.a).error(1006);
        }
    }
}
