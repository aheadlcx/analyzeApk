package cn.v6.sixrooms.login.engines;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class a extends VLAsyncHandler<String> {
    final /* synthetic */ GetRegisterVerificationEngine a;

    a(GetRegisterVerificationEngine getRegisterVerificationEngine) {
        this.a = getRegisterVerificationEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    this.a.a.getVerifyCodeSuccess(string2);
                } else {
                    this.a.a.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                this.a.a.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
