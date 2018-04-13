package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class e extends VLAsyncHandler<String> {
    final /* synthetic */ FreeVoteNumEngine a;

    e(FreeVoteNumEngine freeVoteNumEngine) {
        this.a = freeVoteNumEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    this.a.a.result(string2);
                } else {
                    this.a.a.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
