package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import org.json.JSONException;
import org.json.JSONObject;

final class d extends VLAsyncHandler<String> {
    final /* synthetic */ OrderStatusEngine a;

    d(OrderStatusEngine orderStatusEngine) {
        this.a = orderStatusEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                OrderStatusBean orderStatusBean = new OrderStatusBean();
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if (jSONObject.has("key")) {
                    orderStatusBean.setKey(jSONObject.getString("key"));
                }
                orderStatusBean.setFlag(string);
                orderStatusBean.setContent(string2);
                OrderStatusEngine.a(this.a).handleResult(orderStatusBean);
            } catch (JSONException e) {
                OrderStatusEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            OrderStatusEngine.a(this.a).error(1006);
        }
    }
}
