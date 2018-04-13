package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class a extends VLAsyncHandler<String> {
    final /* synthetic */ CommonEventStatusEngine a;

    a(CommonEventStatusEngine commonEventStatusEngine) {
        this.a = commonEventStatusEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    CommonEventStatusEngine.a(this.a).result((CommonEventStatusBean) JsonParseUtils.json2Obj(string2, CommonEventStatusBean.class));
                    return;
                }
                CommonEventStatusEngine.a(this.a).handleErrorInfo(string, string2);
            } catch (JSONException e) {
                CommonEventStatusEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            CommonEventStatusEngine.a(this.a).error(1006);
        }
    }
}
