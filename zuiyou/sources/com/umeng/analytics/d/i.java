package com.umeng.analytics.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.connect.common.Constants;
import com.umeng.a.b;
import com.umeng.a.d;
import com.umeng.a.e;
import com.umeng.a.g;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.analytics.c.h;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;

public class i {
    private String a;
    private String b = "10.0.0.172";
    private int c = 80;
    private Context d;
    private h e;

    public i(Context context) {
        this.d = context;
        this.a = a(context);
    }

    public void a(h hVar) {
        this.e = hVar;
    }

    private void b() {
        String d = h.a(this.d).b().d("");
        String c = h.a(this.d).b().c("");
        if (!TextUtils.isEmpty(d)) {
            a.f = b.b(d);
        }
        if (!TextUtils.isEmpty(c)) {
            a.g = b.b(c);
        }
        a.h = new String[]{a.f, a.g};
        int b = com.umeng.analytics.e.a.a(this.d).b();
        if (b == -1) {
            return;
        }
        if (b == 0) {
            a.h = new String[]{a.f, a.g};
        } else if (b == 1) {
            a.h = new String[]{a.g, a.f};
        }
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        b();
        for (String a : a.h) {
            bArr2 = a(bArr, a);
            if (bArr2 != null) {
                if (this.e != null) {
                    this.e.c();
                }
                return bArr2;
            }
            if (this.e != null) {
                this.e.d();
            }
        }
        return bArr2;
    }

    private boolean c() {
        if (this.d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.d.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.d.getSystemService("connectivity");
            if (!d.a(this.d, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
        }
    }

    private byte[] a(byte[] bArr, String str) {
        InputStream inputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        try {
            if (this.e != null) {
                this.e.a();
            }
            if (c()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.b, this.c)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.a);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (VERSION.SDK_INT < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.e != null) {
                    this.e.b();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Object headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    headerField = null;
                } else {
                    headerField = 1;
                }
                if (responseCode != 200 || r0 == null) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                g.b("Send message to " + str);
                inputStream = httpURLConnection.getInputStream();
                byte[] b = e.b(inputStream);
                e.c(inputStream);
                if (httpURLConnection == null) {
                    return b;
                }
                httpURLConnection.disconnect();
                return b;
            } catch (Throwable th2) {
                th = th2;
                try {
                    g.a("IOException,Failed to send message.", th);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append("6.1.0");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(d.s(context));
            stringBuffer2.append("/");
            stringBuffer2.append(d.b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(e.a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a() {
        /*
        r10 = this;
        r0 = 0;
        r1 = java.security.KeyStore.getDefaultType();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r1 = java.security.KeyStore.getInstance(r1);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2 = 0;
        r3 = 0;
        r1.load(r2, r3);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2 = new com.umeng.analytics.d.n;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r1 = org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2.setHostnameVerifier(r1);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r1 = new org.apache.http.client.methods.HttpGet;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r3 = "https://uop.umeng.com";
        r1.<init>(r3);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r3 = new org.apache.http.params.BasicHttpParams;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r3.<init>();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = org.apache.http.HttpVersion.HTTP_1_1;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        org.apache.http.params.HttpProtocolParams.setVersion(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = "ISO-8859-1";
        org.apache.http.params.HttpProtocolParams.setContentCharset(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = 1;
        org.apache.http.params.HttpProtocolParams.setUseExpectContinue(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        org.apache.http.conn.params.ConnManagerParams.setTimeout(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        org.apache.http.params.HttpConnectionParams.setSoTimeout(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = new org.apache.http.conn.scheme.SchemeRegistry;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4.<init>();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r5 = new org.apache.http.conn.scheme.Scheme;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r6 = "http";
        r7 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r8 = 80;
        r5.<init>(r6, r7, r8);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4.register(r5);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r5 = new org.apache.http.conn.scheme.Scheme;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r6 = "https";
        r7 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
        r5.<init>(r6, r2, r7);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4.register(r5);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r4.<init>(r2, r3);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r1 = r4.execute(r1);	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r1 = r1.getEntity();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        r0 = r1.getContent();	 Catch:{ Throwable -> 0x0093, all -> 0x00db }
        if (r0 == 0) goto L_0x00d3;
    L_0x007e:
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r1.<init>();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
    L_0x0087:
        r3 = r0.read(r2);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r4 = -1;
        if (r3 == r4) goto L_0x009a;
    L_0x008e:
        r4 = 0;
        r1.write(r2, r4, r3);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        goto L_0x0087;
    L_0x0093:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0099;
    L_0x0096:
        r0.close();	 Catch:{ Throwable -> 0x00e5 }
    L_0x0099:
        return;
    L_0x009a:
        r1.close();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r2 = new java.lang.String;	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r1 = r1.toByteArray();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r3 = "UTF-8";
        r2.<init>(r1, r3);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r1 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        if (r1 != 0) goto L_0x00d3;
    L_0x00af:
        r1 = r2.length();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        if (r1 <= 0) goto L_0x00d3;
    L_0x00b5:
        r1 = r2.length();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r3 = 50;
        if (r1 >= r3) goto L_0x00d3;
    L_0x00bd:
        r1 = r10.d;	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r1 = com.umeng.analytics.d.m.a(r1);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        if (r1 == 0) goto L_0x00d3;
    L_0x00c5:
        r1 = r1.edit();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r3 = "uopdta";
        r1 = r1.putString(r3, r2);	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
        r1.commit();	 Catch:{ Throwable -> 0x0093, all -> 0x00e9 }
    L_0x00d3:
        if (r0 == 0) goto L_0x0099;
    L_0x00d5:
        r0.close();	 Catch:{ Throwable -> 0x00d9 }
        goto L_0x0099;
    L_0x00d9:
        r0 = move-exception;
        goto L_0x0099;
    L_0x00db:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x00df:
        if (r1 == 0) goto L_0x00e4;
    L_0x00e1:
        r1.close();	 Catch:{ Throwable -> 0x00e7 }
    L_0x00e4:
        throw r0;
    L_0x00e5:
        r0 = move-exception;
        goto L_0x0099;
    L_0x00e7:
        r1 = move-exception;
        goto L_0x00e4;
    L_0x00e9:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00df;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.d.i.a():void");
    }
}
