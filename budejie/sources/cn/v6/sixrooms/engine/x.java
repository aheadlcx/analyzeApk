package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import org.json.JSONException;
import org.json.JSONObject;

final class x extends VLAsyncHandler<String> {
    final /* synthetic */ GetCoopTypeEngine a;

    x(GetCoopTypeEngine getCoopTypeEngine) {
        this.a = getCoopTypeEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    JSONObject jSONObject2 = new JSONObject(string2).getJSONObject(AlibcConstants.PF_ANDROID);
                    this.a.a.success(jSONObject2.getString("login"), jSONObject2.getString("pay"));
                    return;
                }
                this.a.a.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
