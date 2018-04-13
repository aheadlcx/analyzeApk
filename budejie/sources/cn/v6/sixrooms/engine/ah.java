package cn.v6.sixrooms.engine;

import android.util.Log;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.ShuMeiParameterUtils;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

final class ah extends VLAsyncHandler<String> {
    final /* synthetic */ PartnerLoginEngine a;

    ah(PartnerLoginEngine partnerLoginEngine) {
        this.a = partnerLoginEngine;
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
                    string2 = jSONObject2.getString("ticket");
                    String string3 = jSONObject2.getString("p");
                    String string4 = jSONObject2.getString("ptime");
                    string = jSONObject2.getString("op");
                    string2 = (UrlStrs.LOGIN_CLIENT + "?ticket=" + URLEncoder.encode(string2) + "&p=" + string3 + "&ptime=" + string4 + "&av=2.7" + ShuMeiParameterUtils.getParameterStr()).replace("|", "%7C");
                    LogUtils.e(PartnerLoginEngine.TAG, string2);
                    Log.e(PartnerLoginEngine.TAG, string2);
                    NetworkServiceSingleton.createInstance().sendAsyncRequest(new ai(this, string), string2, "");
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
