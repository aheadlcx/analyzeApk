package qsbk.app.core.web.js;

import android.os.Handler;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBConstants;
import org.json.JSONObject;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.web.plugin.PluginProxy;
import qsbk.app.core.web.route.RouteProxy;

public class ExposeApi implements IExposeApi {
    private PluginProxy a;
    private RouteProxy b;
    private Handler c = new Handler();

    public ExposeApi(PluginProxy pluginProxy, RouteProxy routeProxy) {
        this.a = pluginProxy;
        this.b = routeProxy;
    }

    public void reqNative(String str, String str2, String str3, String str4) {
        LogUtils.d("web call native:" + str + " action:" + str2 + " callid:" + str3 + " argumengts:" + str4);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("model and action are not allowed to be empty");
        }
        this.c.post(new a(this, str, str2, str3, str4));
    }

    public void onWebResp(String str, String str2) {
        LogUtils.d("on web resp:" + str + " resp:" + str2);
        this.c.post(new b(this, str, str2));
    }

    public void callByCallInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("method");
            if ("reqNative".equals(optString)) {
                reqNative(jSONObject.optString("modul"), jSONObject.optString("action"), jSONObject.optString(WBConstants.SHARE_CALLBACK_ID), jSONObject.optString("args"));
            } else if ("onWebResp".equals(optString)) {
                onWebResp(jSONObject.optString("id"), jSONObject.optString("resp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
