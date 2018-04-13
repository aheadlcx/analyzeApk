package qsbk.app.core.net;

import android.text.TextUtils;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.qiushibaike.httpdns.lib.HttpDNSManager;
import org.json.JSONObject;
import qsbk.app.core.R;
import qsbk.app.core.net.OkHttp.OkhttpDns;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.Logger;

class b implements ErrorListener {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ String c;
    final /* synthetic */ NetworkCallback d;
    final /* synthetic */ boolean e;
    final /* synthetic */ NetRequest f;

    b(NetRequest netRequest, String str, JSONObject jSONObject, String str2, NetworkCallback networkCallback, boolean z) {
        this.f = netRequest;
        this.a = str;
        this.b = jSONObject;
        this.c = str2;
        this.d = networkCallback;
        this.e = z;
    }

    public void onErrorResponse(VolleyError volleyError) {
        String string;
        int i;
        if (Logger.getInstance().isDebug()) {
            Logger.getInstance().debug(NetRequest.b, com.umeng.analytics.pro.b.J, this.a + "\n" + (this.b != null ? this.b.toString() : "") + "\n" + volleyError);
        } else {
            LogUtils.e(NetRequest.b, volleyError.getMessage(), volleyError);
        }
        Object ipByHost = OkhttpDns.getIpByHost(this.c);
        if (!(TextUtils.isEmpty(this.c) || TextUtils.isEmpty(ipByHost))) {
            HttpDNSManager.instance().reportError(this.c, ipByHost);
        }
        String message = volleyError.getMessage();
        if (volleyError instanceof ServerError) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_server_crash);
            i = 1;
        } else if (volleyError instanceof TimeoutError) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_request_timeout);
            i = 0;
        } else if (volleyError instanceof NetworkError) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_error);
            i = 0;
        } else if (volleyError instanceof NoConnectionError) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_state_error);
            i = 0;
        } else if (volleyError instanceof ParseError) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_server_error);
            i = 1;
        } else {
            int i2;
            if (volleyError.networkResponse != null) {
                i2 = volleyError.networkResponse.statusCode;
            } else {
                i2 = 0;
            }
            if (i2 >= 400) {
                string = message;
                i = 1;
            } else {
                string = message;
                i = 0;
            }
        }
        if (!(!TextUtils.isEmpty(string) || volleyError.networkResponse == null || volleyError.networkResponse.data == null)) {
            try {
                string = new JSONObject(new String(volleyError.networkResponse.data)).optString("err_msg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(string)) {
            string = AppUtils.getInstance().getAppContext().getString(R.string.net_error);
        }
        this.f.onFailed(this.d, i, string, this.e);
        this.f.onFinished(this.d);
    }
}
