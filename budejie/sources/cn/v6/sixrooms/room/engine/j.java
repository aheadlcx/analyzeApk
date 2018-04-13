package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

final class j extends VLAsyncHandler<String> {
    final /* synthetic */ RetransmitEngine a;

    j(RetransmitEngine retransmitEngine) {
        this.a = retransmitEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        Exception e;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if (!"001".equals(string)) {
                    this.a.a.handleErrorInfo(string, jSONObject.getString("content"));
                } else if (jSONObject.getString("content").equals("[]")) {
                    this.a.a.error(1111);
                } else {
                    this.a.a.result(RetransmitEngine.a(jSONObject.getJSONObject("content")));
                }
            } catch (JSONException e2) {
                e = e2;
                this.a.a.error(1007);
                e.printStackTrace();
            } catch (UnsupportedEncodingException e3) {
                e = e3;
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
