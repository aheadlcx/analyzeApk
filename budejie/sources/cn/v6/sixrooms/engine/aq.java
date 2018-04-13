package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.RedBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class aq extends VLAsyncHandler<String> {
    final /* synthetic */ RedInfoEngine a;

    aq(RedInfoEngine redInfoEngine) {
        this.a = redInfoEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    this.a.a.result((RedBean) JsonParseUtils.json2Obj(jSONObject.getJSONObject("content").toString(), RedBean.class));
                    return;
                }
                this.a.a.handerError(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                this.a.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.a.a != null) {
            this.a.a.error(1006);
        }
    }
}
