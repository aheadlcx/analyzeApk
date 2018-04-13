package com.sina.weibo.sdk.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.entity.mime.MIME;

public class b {

    private static class a implements HostnameVerifier {
        private a() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    public static HttpURLConnection a(String str, Context context) {
        HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str) || !(str.startsWith("http://") || str.startsWith("https://"))) {
            throw new RuntimeException("非法url请求");
        }
        HttpURLConnection httpURLConnection2;
        try {
            Proxy proxy;
            URL url = new URL(str);
            Pair a = NetStateManager.a();
            if (a != null) {
                proxy = new Proxy(Type.HTTP, new InetSocketAddress((String) a.first, ((Integer) a.second).intValue()));
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
                httpURLConnection2.setRequestProperty(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
                httpURLConnection2.setRequestProperty("Connection", "Keep-Alive");
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
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new a());
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(c.a(context));
                httpURLConnection2 = httpURLConnection;
            } catch (MalformedURLException e2) {
                httpURLConnection2 = httpURLConnection;
            } catch (IOException e3) {
                httpURLConnection2 = httpURLConnection;
            }
            httpURLConnection2.setUseCaches(false);
            httpURLConnection2.setRequestMethod("POST");
            httpURLConnection2.setRequestProperty(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
            httpURLConnection2.setRequestProperty("Connection", "Keep-Alive");
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
