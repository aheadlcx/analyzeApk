package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class af extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ NameModifyEngine b;

    af(NameModifyEngine nameModifyEngine, String str) {
        this.b = nameModifyEngine;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if (string.equals("001")) {
                    this.b.a.result(jSONObject.getString("content"), this.a);
                } else {
                    this.b.a.handleErrorInfo(string, jSONObject.getString("content"));
                }
            } catch (JSONException e) {
                this.b.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.a != null) {
            this.b.a.error(1006);
        }
    }
}
