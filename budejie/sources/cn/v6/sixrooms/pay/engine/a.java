package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class a extends VLAsyncHandler<String> {
    final /* synthetic */ H5WeixinPayEngine a;

    a(H5WeixinPayEngine h5WeixinPayEngine) {
        this.a = h5WeixinPayEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    H5WeixinPay h5WeixinPay = (H5WeixinPay) JsonParseUtils.json2Obj(string2, H5WeixinPay.class);
                    this.a.a.handleResutl(h5WeixinPay, jSONObject.getString("key"));
                    return;
                }
                this.a.a.handleError(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
