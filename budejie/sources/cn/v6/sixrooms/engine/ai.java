package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.socket.IM.IMSocketUtil;
import org.json.JSONException;
import org.json.JSONObject;

final class ai extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ ah b;

    ai(ah ahVar, String str) {
        this.b = ahVar;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    this.b.a.a.loginSuccess(this.a, jSONObject.getString("key"));
                } else if (IMSocketUtil.INNER_TYPE_ID_LOGIN_BAD.equals(string)) {
                    this.b.a.a.bundlePhone(this.a, jSONObject.getString("key"));
                } else {
                    this.b.a.a.handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                this.b.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.a.a != null) {
            this.b.a.a.error(1006);
        }
    }
}
