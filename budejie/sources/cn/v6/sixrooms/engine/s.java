package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import org.json.JSONException;
import org.json.JSONObject;

final class s extends VLAsyncHandler<String> {
    final /* synthetic */ FindUsernameEngine a;

    s(FindUsernameEngine findUsernameEngine) {
        this.a = findUsernameEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    FindUsernameEngine.a(this.a).findUsernameSucceed(FindUsernameEngine.a(jSONObject.getJSONObject("content").getString(HistoryOpenHelper.COLUMN_USERNAME)));
                    return;
                }
                FindUsernameEngine.a(this.a).handleErrorInfo(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                FindUsernameEngine.a(this.a).error(1007);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && FindUsernameEngine.a(this.a) != null) {
            FindUsernameEngine.a(this.a).error(1006);
        }
    }
}
