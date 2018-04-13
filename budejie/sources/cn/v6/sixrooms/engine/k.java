package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

final class k extends VLAsyncHandler<String> {
    final /* synthetic */ CommodityInfoEngine a;

    k(CommodityInfoEngine commodityInfoEngine) {
        this.a = commodityInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = new JSONObject(string2);
                    List arrayList = new ArrayList();
                    arrayList.add(CommodityInfoEngine.a(jSONObject2.getJSONObject(IXAdRequestInfo.GPS)));
                    arrayList.add(CommodityInfoEngine.a(jSONObject2.getJSONObject("s")));
                    this.a.b.success(arrayList);
                    return;
                }
                this.a.b.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.b.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.b != null) {
            this.a.b.error(1006);
        }
    }
}
