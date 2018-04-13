package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.utils.LogUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

final class b extends VLAsyncHandler<String> {
    final /* synthetic */ MakeOrderEngine a;

    b(MakeOrderEngine makeOrderEngine) {
        this.a = makeOrderEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("1".equals(string)) {
                    OrderBean orderBean = new OrderBean();
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    LogUtils.i("MakeOrderEwngine", "content = " + jSONObject2);
                    String string2 = jSONObject.getString("key");
                    String string3 = jSONObject2.getString("orderid");
                    String string4 = jSONObject2.getString("msg");
                    try {
                        URLDecoder.decode(string4, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    orderBean.setMsg(string4);
                    orderBean.setKey(string2);
                    orderBean.setOrderid(string3);
                    MakeOrderEngine.a(this.a).handleResult(string, orderBean);
                    return;
                }
                MakeOrderEngine.a(this.a).handleResult(string, null);
            } catch (JSONException e2) {
                MakeOrderEngine.a(this.a).error(1007);
                e2.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            MakeOrderEngine.a(this.a).error(1006);
        }
    }
}
