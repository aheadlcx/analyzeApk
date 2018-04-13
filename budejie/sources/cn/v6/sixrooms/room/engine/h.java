package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.bean.OperatorFlowBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class h extends VLAsyncHandler<String> {
    final /* synthetic */ OperatorFlowEngine a;

    h(OperatorFlowEngine operatorFlowEngine) {
        this.a = operatorFlowEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    OperatorFlowEngine.a(this.a).result((OperatorFlowBean) JsonParseUtils.json2Obj(string2, OperatorFlowBean.class));
                    return;
                }
                OperatorFlowEngine.a(this.a).handleErrorInfo(string, string2);
            } catch (JSONException e) {
                OperatorFlowEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            OperatorFlowEngine.a(this.a).error(1006);
        }
    }
}
