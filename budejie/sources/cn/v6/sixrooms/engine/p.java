package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class p extends VLAsyncHandler<String> {
    final /* synthetic */ ExchangeBean6ToCoin6Engine a;

    p(ExchangeBean6ToCoin6Engine exchangeBean6ToCoin6Engine) {
        this.a = exchangeBean6ToCoin6Engine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if (string.equals("001")) {
                    ExchangeBean6ToCoin6Engine.a(this.a).result(string2);
                } else {
                    ExchangeBean6ToCoin6Engine.a(this.a).handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                ExchangeBean6ToCoin6Engine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && ExchangeBean6ToCoin6Engine.a(this.a) != null) {
            ExchangeBean6ToCoin6Engine.a(this.a).error(1006);
        }
    }
}
