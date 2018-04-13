package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class h extends VLAsyncHandler<String> {
    final /* synthetic */ BundlePhoneEngine a;

    h(BundlePhoneEngine bundlePhoneEngine) {
        this.a = bundlePhoneEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    this.a.a.bundleSucceed(jSONObject2.getString("msg"), jSONObject2.getString("mission"));
                    return;
                }
                this.a.a.handleErrorInfo(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                this.a.a.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
