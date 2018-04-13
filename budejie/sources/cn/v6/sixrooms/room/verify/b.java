package cn.v6.sixrooms.room.verify;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class b extends VLAsyncHandler<String> {
    final /* synthetic */ VerifyEngine a;

    b(VerifyEngine verifyEngine) {
        this.a = verifyEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    VerifyEngine.a(this.a).result(string2);
                } else {
                    VerifyEngine.a(this.a).handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                VerifyEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            VerifyEngine.a(this.a).error(1006);
        }
    }
}
