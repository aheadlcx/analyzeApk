package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.io.Closeable;
import java.net.HttpURLConnection;

class ay extends Thread {
    private Context a;
    private l b;

    public ay(Context context, l lVar) {
        this.a = context;
        this.b = lVar;
    }

    public void run() {
        try {
            int i = bb.a ? 3 : 10;
            bd.a("start version check in " + i + "s");
            sleep((long) (i * 1000));
            a();
            a(this.a);
        } catch (Throwable e) {
            bd.a(e);
        }
        ax.b = false;
    }

    private void a(Context context) {
        this.b.a(context, System.currentTimeMillis());
    }

    private synchronized void a() {
        bd.a("start get config and download jar");
        Context context = this.a;
        l lVar = this.b;
        String b = b(context);
        bd.c("update req url is:" + b);
        HttpURLConnection d = cu.d(context, b);
        try {
            d.connect();
            String headerField = d.getHeaderField("X-CONFIG");
            bd.a("config is: " + headerField);
            Object headerField2 = d.getHeaderField("X-SIGN");
            bd.a("sign is: " + headerField2);
            int responseCode = d.getResponseCode();
            bd.a("update response code is: " + responseCode);
            int contentLength = d.getContentLength();
            bd.a("update response content length is: " + contentLength);
            if (responseCode == 200 && contentLength > 0) {
                Closeable openFileOutput = context.openFileOutput(".remote.jar", 0);
                if (da.a(d.getInputStream(), openFileOutput)) {
                    bd.a("save remote jar success");
                }
                da.a(openFileOutput);
            }
        } catch (Throwable e) {
            bd.b(e);
            da.a(null);
        } catch (Throwable th) {
            d.disconnect();
        }
        ax.a = null;
        au.a();
        if (!TextUtils.isEmpty(headerField)) {
            lVar.a(context, headerField);
        }
        if (!TextUtils.isEmpty(headerField2)) {
            lVar.b(context, headerField2);
        }
        d.disconnect();
        bd.a("finish get config and download jar");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(android.content.Context r7) {
        /*
        r6 = this;
        r0 = ".remote.jar";
        r0 = r7.getFileStreamPath(r0);
        r1 = "14";
        if (r0 == 0) goto L_0x00f7;
    L_0x000a:
        r0 = r0.exists();
        if (r0 == 0) goto L_0x00f7;
    L_0x0010:
        r0 = ".remote.jar";
        r0 = r7.getFileStreamPath(r0);
        if (r0 == 0) goto L_0x00f7;
    L_0x0018:
        r0 = r0.getAbsolutePath();
        r0 = com.baidu.mobstat.ax.b(r0);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "startDownload remote jar file version = ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.baidu.mobstat.bd.a(r2);
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x00f7;
    L_0x003c:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = new org.apache.http.message.BasicNameValuePair;
        r3 = "dynamicVersion";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.<init>(r3, r0);
        r1.add(r2);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "packageName";
        r3 = com.baidu.mobstat.de.p(r7);
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "appVersion";
        r3 = com.baidu.mobstat.de.f(r7);
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "cuid";
        r3 = com.baidu.mobstat.de.a(r7);
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "platform";
        r3 = "Android";
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "m";
        r3 = android.os.Build.MODEL;
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "s";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = android.os.Build.VERSION.SDK_INT;
        r3 = r3.append(r4);
        r4 = "";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "o";
        r3 = android.os.Build.VERSION.RELEASE;
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = new org.apache.http.message.BasicNameValuePair;
        r2 = "i";
        r3 = "14";
        r0.<init>(r2, r3);
        r1.add(r0);
        r0 = "utf-8";
        r0 = org.apache.http.client.utils.URLEncodedUtils.format(r1, r0);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = com.baidu.mobstat.bb.c;
        r1 = r1.append(r2);
        r2 = "?";
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
        return r0;
    L_0x00f7:
        r0 = r1;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ay.b(android.content.Context):java.lang.String");
    }
}
