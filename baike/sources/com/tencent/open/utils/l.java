package com.tencent.open.utils;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.f;
import com.tencent.tauth.IRequestListener;
import org.json.JSONObject;

final class l extends Thread {
    final /* synthetic */ QQToken a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ Bundle d;
    final /* synthetic */ String e;
    final /* synthetic */ IRequestListener f;

    l(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
        this.a = qQToken;
        this.b = context;
        this.c = str;
        this.d = bundle;
        this.e = str2;
        this.f = iRequestListener;
    }

    public void run() {
        try {
            JSONObject request = HttpUtils.request(this.a, this.b, this.c, this.d, this.e);
            if (this.f != null) {
                this.f.onComplete(request);
                f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
            }
        } catch (Throwable e) {
            if (this.f != null) {
                this.f.onMalformedURLException(e);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", e);
            }
        } catch (Throwable e2) {
            if (this.f != null) {
                this.f.onConnectTimeoutException(e2);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", e2);
            }
        } catch (Throwable e22) {
            if (this.f != null) {
                this.f.onSocketTimeoutException(e22);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", e22);
            }
        } catch (Throwable e222) {
            if (this.f != null) {
                this.f.onNetworkUnavailableException(e222);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", e222);
            }
        } catch (Throwable e2222) {
            if (this.f != null) {
                this.f.onHttpStatusException(e2222);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", e2222);
            }
        } catch (Throwable e22222) {
            if (this.f != null) {
                this.f.onIOException(e22222);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", e22222);
            }
        } catch (Throwable e222222) {
            if (this.f != null) {
                this.f.onJSONException(e222222);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", e222222);
            }
        } catch (Throwable e2222222) {
            if (this.f != null) {
                this.f.onUnknowException(e2222222);
                f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", e2222222);
            }
        }
    }
}
