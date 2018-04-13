package qsbk.app.live.ui;

import android.os.AsyncTask;
import android.os.Build;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.Activity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.web.ui.QsbkWebView;

class m extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ Activity a;
    final /* synthetic */ QsbkWebView b;
    final /* synthetic */ LiveBaseActivity c;

    m(LiveBaseActivity liveBaseActivity, Activity activity, QsbkWebView qsbkWebView) {
        this.c = liveBaseActivity;
        this.a = activity;
        this.b = qsbkWebView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    protected Boolean a(Void... voidArr) {
        Exception exception;
        Boolean valueOf;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.a.pic_web_url).openConnection();
            try {
                boolean z = httpURLConnection2.getResponseCode() == 200;
                httpURLConnection2.getInputStream().close();
                Boolean valueOf2 = Boolean.valueOf(z);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return valueOf2;
            } catch (Exception e) {
                Exception exception2 = e;
                httpURLConnection = httpURLConnection2;
                exception = exception2;
                try {
                    exception.printStackTrace();
                    valueOf = Boolean.valueOf(false);
                    if (httpURLConnection != null) {
                        return valueOf;
                    }
                    httpURLConnection.disconnect();
                    return valueOf;
                } catch (Throwable th2) {
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection = httpURLConnection2;
                th = th4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            valueOf = Boolean.valueOf(false);
            if (httpURLConnection != null) {
                return valueOf;
            }
            httpURLConnection.disconnect();
            return valueOf;
        }
    }

    protected void a(Boolean bool) {
        if (bool.booleanValue()) {
            Map hashMap = new HashMap();
            hashMap.put("app_ver", DeviceUtils.getAppVersion());
            hashMap.put("device_info", DeviceUtils.getDeviceIdInfo());
            hashMap.put("model", Build.FINGERPRINT);
            if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                hashMap.put("qbtoken", AppUtils.getInstance().getUserInfoProvider().getToken());
                hashMap.put("user_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                hashMap.put("app", Constants.APP_FLAG + "");
            }
            this.b.loadUrl(this.a.pic_web_url, hashMap);
            LiveBaseActivity.e(this.c).addView(this.b);
            LiveBaseActivity.f(this.c).add(this.b);
            return;
        }
        this.b.onDestroy();
        this.b.destroy();
    }
}
