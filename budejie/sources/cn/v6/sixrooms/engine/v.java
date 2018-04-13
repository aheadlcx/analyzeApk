package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class v extends VLAsyncHandler<String> {
    final /* synthetic */ GetAuthCodeEngine a;

    v(GetAuthCodeEngine getAuthCodeEngine) {
        this.a = getAuthCodeEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                LogUtils.i(GetAuthCodeEngine.a, "result_verifyCode==content" + string2);
                if ("001".equals(string)) {
                    this.a.b.verifyCodeSucceed(string2);
                } else {
                    this.a.b.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                this.a.b.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.b != null) {
            this.a.b.error(1006);
        }
    }
}
