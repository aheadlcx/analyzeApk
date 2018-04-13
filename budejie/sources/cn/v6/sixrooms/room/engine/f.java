package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class f extends VLAsyncHandler<String> {
    final /* synthetic */ InitHeadLineEngine a;

    f(InitHeadLineEngine initHeadLineEngine) {
        this.a = initHeadLineEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                String str = (String) getParam();
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    this.a.a.result((InitHeadLineBean) JsonParseUtils.json2Obj(str, InitHeadLineBean.class));
                    return;
                }
                this.a.a.handleErrorInfo(string, string2);
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            this.a.a.error(1006);
        }
    }
}
