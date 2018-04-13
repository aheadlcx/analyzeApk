package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import com.ixintui.pushsdk.SdkConstants;
import org.json.JSONException;
import org.json.JSONObject;

final class c extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ LoginClientEngine b;

    c(LoginClientEngine loginClientEngine, String str) {
        this.b = loginClientEngine;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z || !CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                if (string2.equals("001")) {
                    this.b.a.loginClientSuccess(this.a, SdkConstants.REGISTER);
                    return;
                } else {
                    this.b.a.handleErrorInfo(string2, string);
                    return;
                }
            } catch (JSONException e) {
                this.b.a.error(1007);
                return;
            }
        }
        this.b.a.error(1006);
    }
}
