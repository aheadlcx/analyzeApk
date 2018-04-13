package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class g extends VLAsyncHandler<String> {
    final /* synthetic */ BundleInfoEngine a;

    g(BundleInfoEngine bundleInfoEngine) {
        this.a = bundleInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    this.a.a.bundleInfo(jSONObject2.getString("boundled"), jSONObject2.getString("mobile"), jSONObject2.getString("needpaawd"));
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