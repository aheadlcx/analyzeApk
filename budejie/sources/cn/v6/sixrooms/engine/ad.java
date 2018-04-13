package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class ad extends VLAsyncHandler<String> {
    final /* synthetic */ IsFollowEngine a;

    ad(IsFollowEngine isFollowEngine) {
        this.a = isFollowEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if (!"001".equals(string)) {
                    this.a.a.handleErrorInfo(string, string2);
                } else if ("0".equals(string2)) {
                    this.a.a.result(false);
                } else if ("1".equals(string2)) {
                    this.a.a.result(true);
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
