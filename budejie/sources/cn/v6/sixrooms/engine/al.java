package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.socket.IM.IMSocketUtil;
import org.json.JSONException;
import org.json.JSONObject;

final class al extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ PassportLoginEngine b;

    al(PassportLoginEngine passportLoginEngine, String str) {
        this.b = passportLoginEngine;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                Object obj = -1;
                switch (string2.hashCode()) {
                    case 47665:
                        if (string2.equals("001")) {
                            obj = null;
                            break;
                        }
                        break;
                    case 52470:
                        if (string2.equals(IMSocketUtil.INNER_TYPE_ID_USER_NO_LOGIN)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        this.b.b.loginSuccess(1000, this.a);
                        return;
                    case 1:
                        this.b.b.otherPlaceLogin(string, this.a);
                        return;
                    default:
                        this.b.b.loginSuccess(1009, null);
                        return;
                }
            } catch (JSONException e) {
                this.b.b.loginSuccess(1009, null);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.b != null) {
            this.b.b.error(1006);
        }
    }
}
