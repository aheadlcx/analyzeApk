package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class t extends VLAsyncHandler<String> {
    final /* synthetic */ GainMobileStarsEngine a;

    t(GainMobileStarsEngine gainMobileStarsEngine) {
        this.a = gainMobileStarsEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                if ("001".equals(string2)) {
                    this.a.a.result(string2, string);
                } else {
                    this.a.a.handleErrorInfo(string2, string);
                }
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
