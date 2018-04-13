package qsbk.app.core.net;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.android.volley.Response.Listener;
import com.qiushibaike.httpdns.lib.HttpDNSManager;
import org.json.JSONObject;
import qsbk.app.core.model.LoginData;
import qsbk.app.core.net.OkHttp.OkhttpDns;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;

class a implements Listener<JSONObject> {
    final /* synthetic */ String a;
    final /* synthetic */ NetworkCallback b;
    final /* synthetic */ boolean c;
    final /* synthetic */ NetRequest d;

    a(NetRequest netRequest, String str, NetworkCallback networkCallback, boolean z) {
        this.d = netRequest;
        this.a = str;
        this.b = networkCallback;
        this.c = z;
    }

    public void onResponse(JSONObject jSONObject) {
        LogUtils.d(NetRequest.b, "response: " + jSONObject.toString());
        Object ipByHost = OkhttpDns.getIpByHost(this.a);
        if (!(TextUtils.isEmpty(this.a) || TextUtils.isEmpty(ipByHost))) {
            HttpDNSManager.instance().reportOK(this.a, ipByHost);
        }
        try {
            int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
            String optString = jSONObject.optString("err_msg");
            String optString2 = jSONObject.optString("renew_token", "");
            if (!TextUtils.isEmpty(optString2)) {
                AppUtils.getInstance().getUserInfoProvider().setToken(optString2);
                LoginData.updateToken(AppUtils.getInstance().getUserInfoProvider().getUser(), optString2);
                Object optString3 = jSONObject.optString("renew_usersig", "");
                if (!TextUtils.isEmpty(optString3)) {
                    AppUtils.getInstance().getUserInfoProvider().setUserSig(optString3);
                }
            }
            if (optInt == 0) {
                this.d.onSuccess(this.b, jSONObject);
            } else if (-401 == optInt) {
                AppUtils.getInstance().getUserInfoProvider().onLogout(optString);
                this.d.c();
            } else if (-9999 == optInt) {
                ipByHost = jSONObject.optString("ts_diff");
                if (!TextUtils.isEmpty(ipByHost)) {
                    NetRequest.e = Long.parseLong(ipByHost) + NetRequest.e;
                }
            } else {
                this.d.onFailed(this.b, optInt, optString, this.c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.d.onFailed(this.b, 1, e.getMessage(), this.c);
        }
        this.d.onFinished(this.b);
    }
}
