package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class k extends VLAsyncHandler<String> {
    final /* synthetic */ String a;

    k(String str) {
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    LogUtils.e(WebGiftEngine.a, string2);
                } else {
                    LogUtils.e(WebGiftEngine.a, string + ":" + string2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
            WebGiftEngine.uploadGiftSlowData(this.a);
        }
    }
}
