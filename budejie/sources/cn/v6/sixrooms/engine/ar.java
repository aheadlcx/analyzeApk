package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class ar extends VLAsyncHandler<String> {
    final /* synthetic */ RefreshChatListEngine a;

    ar(RefreshChatListEngine refreshChatListEngine) {
        this.a = refreshChatListEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                if ("001".equals(jSONObject.getString("flag"))) {
                    RefreshChatListEngine.a(this.a, jSONObject);
                } else {
                    this.a.a.resultInfo(null);
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
