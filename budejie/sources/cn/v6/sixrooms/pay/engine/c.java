package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.bean.OrderBean;
import org.json.JSONException;
import org.json.JSONObject;

final class c extends VLAsyncHandler<String> {
    final /* synthetic */ MakeOrderEngine$CoopRechargeCallBack a;
    final /* synthetic */ MakeOrderEngine b;

    c(MakeOrderEngine makeOrderEngine, MakeOrderEngine$CoopRechargeCallBack makeOrderEngine$CoopRechargeCallBack) {
        this.b = makeOrderEngine;
        this.a = makeOrderEngine$CoopRechargeCallBack;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    OrderBean orderBean = new OrderBean();
                    jSONObject = jSONObject.getJSONObject("content");
                    orderBean.setOrderid(jSONObject.getString("orderNum"));
                    orderBean.setPrice(jSONObject.getString("price"));
                    this.a.handleResult(string, orderBean);
                    return;
                }
                this.a.handleError(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                this.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.error(1006);
        }
    }
}
