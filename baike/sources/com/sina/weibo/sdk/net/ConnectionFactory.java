package com.sina.weibo.sdk.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ConnectionFactory {
    public static HttpURLConnection createConnect(String str, Context context) {
        HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str) || !(str.startsWith("http://") || str.startsWith("https://"))) {
            throw new RuntimeException("非法url请求");
        }
        HttpURLConnection httpURLConnection2;
        try {
            Proxy proxy;
            URL url = new URL(str);
            Pair apn = NetStateManager.getAPN();
            if (apn != null) {
                proxy = new Proxy(Type.HTTP, new InetSocketAddress((String) apn.first, ((Integer) apn.second).intValue()));
            } else {
                proxy = null;
            }
            if (str.startsWith("http://")) {
                httpURLConnection2 = proxy == null ? (HttpURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection(proxy);
                httpURLConnection2.setUseCaches(false);
                try {
                    httpURLConnection2.setRequestMethod("POST");
                } catch (ProtocolException e) {
                }
                httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection2.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
                httpURLConnection2.setRequestProperty("Charset", "UTF-8");
                httpURLConnection2.setReadTimeout(20000);
                httpURLConnection2.setConnectTimeout(25000);
                return httpURLConnection2;
            }
            if (proxy == null) {
                httpURLConnection = (HttpsURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpsURLConnection) url.openConnection(proxy);
            }
            try {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(HttpsHelper.getSSL(context));
                httpURLConnection2 = httpURLConnection;
            } catch (MalformedURLException e2) {
                httpURLConnection2 = httpURLConnection;
            } catch (IOException e3) {
                httpURLConnection2 = httpURLConnection;
            }
            httpURLConnection2.setUseCaches(false);
            httpURLConnection2.setRequestMethod("POST");
            httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection2.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
            httpURLConnection2.setRequestProperty("Charset", "UTF-8");
            httpURLConnection2.setReadTimeout(20000);
            httpURLConnection2.setConnectTimeout(25000);
            return httpURLConnection2;
        } catch (MalformedURLException e4) {
            httpURLConnection2 = null;
        } catch (IOException e5) {
            httpURLConnection2 = null;
        }
    }
}
