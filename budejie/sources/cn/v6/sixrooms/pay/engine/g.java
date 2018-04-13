package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import org.json.JSONException;
import org.json.JSONObject;

final class g extends VLAsyncHandler<String> {
    final /* synthetic */ YeepayCardStatusEngine a;

    g(YeepayCardStatusEngine yeepayCardStatusEngine) {
        this.a = yeepayCardStatusEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                OrderStatusBean orderStatusBean = new OrderStatusBean();
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                String string3 = jSONObject.getString("key");
                orderStatusBean.setFlag(string);
                orderStatusBean.setContent(string2);
                orderStatusBean.setKey(string3);
                YeepayCardStatusEngine.a(this.a).handleResult(orderStatusBean);
            } catch (JSONException e) {
                YeepayCardStatusEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            YeepayCardStatusEngine.a(this.a).error(1006);
        }
    }
}
