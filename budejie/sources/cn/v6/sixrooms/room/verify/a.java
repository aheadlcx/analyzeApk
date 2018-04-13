package cn.v6.sixrooms.room.verify;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class a extends VLAsyncHandler<String> {
    final /* synthetic */ CaptchaEngine a;

    a(CaptchaEngine captchaEngine) {
        this.a = captchaEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    CaptchaEngine.a(this.a).result((CaptchaBean) JsonParseUtils.json2Obj(string2, CaptchaBean.class));
                    return;
                }
                CaptchaEngine.a(this.a).handleErrorInfo(string, string2);
            } catch (JSONException e) {
                CaptchaEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            CaptchaEngine.a(this.a).error(1006);
        }
    }
}
