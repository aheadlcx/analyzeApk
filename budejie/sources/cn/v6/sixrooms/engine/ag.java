package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class ag extends VLAsyncHandler<String> {
    final /* synthetic */ OtherPlaceLoginEngine a;

    ag(OtherPlaceLoginEngine otherPlaceLoginEngine) {
        this.a = otherPlaceLoginEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    this.a.a.loginsucceed(string2, jSONObject.getString("key"));
                    return;
                }
                this.a.a.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
