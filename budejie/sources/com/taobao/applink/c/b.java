package com.taobao.applink.c;

import android.text.TextUtils;
import android.util.Log;
import com.taobao.applink.TBAppLinkSDK;
import com.taobao.applink.util.TBAppLinkUtil;
import com.taobao.applink.util.d;
import com.taobao.applink.util.e;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static volatile b c = null;
    private CharSequence a;
    private String b;
    private Map d;
    private a e;

    public interface a {
        void a(a aVar);
    }

    private b() {
        this.a = "unkown";
        this.d = new HashMap();
        this.a = b(d.a(TBAppLinkUtil.getApplication()));
        this.b = TBAppLinkSDK.getInstance().sOpenParam.mAppkey;
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    private String b(String str) {
        return !TextUtils.isEmpty(str) ? "a" + new StringBuilder(str).reverse().toString() + "z" : "";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(com.taobao.applink.c.b.a r12) {
        /*
        r11 = this;
        r1 = 0;
        r0 = "https://nbsdk-baichuan.alicdn.com/2.0.0/applink.htm?plat=android&appKey=%s";
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = r11.b;
        r2[r3] = r4;
        r0 = java.lang.String.format(r0, r2);
        r2 = "flush url";
        android.util.Log.d(r2, r0);
        r2 = new java.net.URL;	 Catch:{ Exception -> 0x0204, all -> 0x01e7 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0204, all -> 0x01e7 }
        r0 = r2.openConnection();	 Catch:{ Exception -> 0x0204, all -> 0x01e7 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0204, all -> 0x01e7 }
        r2 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setConnectTimeout(r2);	 Catch:{ Exception -> 0x0208, all -> 0x01f9 }
        r2 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setReadTimeout(r2);	 Catch:{ Exception -> 0x0208, all -> 0x01f9 }
        r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x0208, all -> 0x01f9 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x01cf;
    L_0x0031:
        r1 = r0.getInputStream();	 Catch:{ Exception -> 0x0208, all -> 0x01f9 }
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "UTF-8";
        r3.<init>(r1, r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
    L_0x0046:
        r4 = r2.readLine();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r4 == 0) goto L_0x007b;
    L_0x004c:
        r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        goto L_0x0046;
    L_0x0050:
        r2 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x0054:
        r2 = new java.lang.StringBuffer;	 Catch:{ all -> 0x01fe }
        r2.<init>();	 Catch:{ all -> 0x01fe }
        r3 = "isSuccess=0";
        r3 = r2.append(r3);	 Catch:{ all -> 0x01fe }
        r4 = "&configExist=0";
        r3.append(r4);	 Catch:{ all -> 0x01fe }
        r3 = com.taobao.applink.util.TBAppLinkUtil.getConfigUrl();	 Catch:{ all -> 0x01fe }
        r4 = 0;
        r2 = r2.toString();	 Catch:{ all -> 0x01fe }
        r11.b(r3, r4, r2);	 Catch:{ all -> 0x01fe }
        if (r1 == 0) goto L_0x0075;
    L_0x0072:
        r1.disconnect();
    L_0x0075:
        if (r0 == 0) goto L_0x007a;
    L_0x0077:
        r0.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x007a:
        return;
    L_0x007b:
        r2 = "flush config";
        r4 = r3.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        android.util.Log.d(r2, r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r3 != 0) goto L_0x0098;
    L_0x0091:
        r3 = new com.taobao.applink.c.a;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r11.e = r3;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
    L_0x0098:
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.a(r2);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.a();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r3 != 0) goto L_0x00d1;
    L_0x00a5:
        r2 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = "isSuccess=1";
        r3 = r2.append(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&configExist=0";
        r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = com.taobao.applink.util.TBAppLinkUtil.getConfigUrl();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = 0;
        r2 = r2.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r11.b(r3, r4, r2);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r0 == 0) goto L_0x00c6;
    L_0x00c3:
        r0.disconnect();
    L_0x00c6:
        if (r1 == 0) goto L_0x007a;
    L_0x00c8:
        r1.close();	 Catch:{ IOException -> 0x00cc }
        goto L_0x007a;
    L_0x00cc:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007a;
    L_0x00d1:
        r3 = "appLinkConfig";
        r2 = r2.optJSONObject(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r2 != 0) goto L_0x0107;
    L_0x00d9:
        r2 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = "isSuccess=0";
        r3 = r2.append(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&configExist=0";
        r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = com.taobao.applink.util.TBAppLinkUtil.getConfigUrl();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = 0;
        r2 = r2.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r11.b(r3, r4, r2);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r0 == 0) goto L_0x00fa;
    L_0x00f7:
        r0.disconnect();
    L_0x00fa:
        if (r1 == 0) goto L_0x007a;
    L_0x00fc:
        r1.close();	 Catch:{ IOException -> 0x0101 }
        goto L_0x007a;
    L_0x0101:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007a;
    L_0x0107:
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "exp";
        r4 = r2.optLong(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.d = r4;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "taobao_scheme";
        r4 = r2.optString(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.b = r4;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "tmall_scheme";
        r4 = r2.optString(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.c = r4;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "sign";
        r2 = r2.optString(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.f = r2;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2 = r11.d;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r2 != 0) goto L_0x013a;
    L_0x0133:
        r2 = new java.util.HashMap;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r11.d = r2;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
    L_0x013a:
        r2 = r11.d;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.b;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.remove(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2 = r11.d;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r11.b;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r6 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r6 = r6.d;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 * r8;
        r4 = r4 + r6;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        if (r12 == 0) goto L_0x0167;
    L_0x0162:
        r2 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r12.a(r2);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
    L_0x0167:
        r2 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r2.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = "isSuccess=1";
        r3 = r2.append(r3);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&configExist=1";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&appkeyExist=1";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&taobao_scheme=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r4.b;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&tmall_scheme=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r4.c;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&sug=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r4.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&sign=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r4.f;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = "&exp=";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r11.e;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = r4.d;	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3.append(r4);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r3 = com.taobao.applink.util.TBAppLinkUtil.getConfigUrl();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r4 = 0;
        r2 = r2.toString();	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
        r11.b(r3, r4, r2);	 Catch:{ Exception -> 0x0050, all -> 0x01f9 }
    L_0x01cf:
        if (r0 == 0) goto L_0x01d4;
    L_0x01d1:
        r0.disconnect();
    L_0x01d4:
        if (r1 == 0) goto L_0x007a;
    L_0x01d6:
        r1.close();	 Catch:{ IOException -> 0x01db }
        goto L_0x007a;
    L_0x01db:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007a;
    L_0x01e1:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007a;
    L_0x01e7:
        r0 = move-exception;
        r2 = r1;
    L_0x01e9:
        if (r2 == 0) goto L_0x01ee;
    L_0x01eb:
        r2.disconnect();
    L_0x01ee:
        if (r1 == 0) goto L_0x01f3;
    L_0x01f0:
        r1.close();	 Catch:{ IOException -> 0x01f4 }
    L_0x01f3:
        throw r0;
    L_0x01f4:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x01f3;
    L_0x01f9:
        r2 = move-exception;
        r10 = r2;
        r2 = r0;
        r0 = r10;
        goto L_0x01e9;
    L_0x01fe:
        r2 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x01e9;
    L_0x0204:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0054;
    L_0x0208:
        r2 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.applink.c.b.b(com.taobao.applink.c.b$a):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
        r7 = this;
        r2 = 0;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        java.lang.System.currentTimeMillis();
        r0 = "logtype=2&wappkey=";
        r0 = r1.append(r0);
        r3 = r7.b;
        r0 = r0.append(r3);
        r3 = "&";
        r0 = r0.append(r3);
        r3 = "packagename=";
        r0 = r0.append(r3);
        r3 = com.taobao.applink.util.TBAppLinkUtil.getApplication();
        r3 = com.taobao.applink.b.a.a(r3);
        r0 = r0.append(r3);
        r3 = "&";
        r0 = r0.append(r3);
        r3 = "os=";
        r0 = r0.append(r3);
        r3 = "android";
        r0 = r0.append(r3);
        r3 = "&";
        r0 = r0.append(r3);
        r3 = "deviceid=";
        r0 = r0.append(r3);
        r3 = r7.a;
        r0 = r0.append(r3);
        r3 = "&t=";
        r0 = r0.append(r3);
        r3 = "&sdkversion=";
        r0 = r0.append(r3);
        r3 = "2.0.0";
        r0.append(r3);
        r0 = android.text.TextUtils.isEmpty(r10);
        if (r0 != 0) goto L_0x0071;
    L_0x0068:
        r0 = "&";
        r0 = r1.append(r0);
        r0.append(r10);
    L_0x0071:
        r0 = android.text.TextUtils.isEmpty(r9);
        if (r0 != 0) goto L_0x0080;
    L_0x0077:
        r0 = "&type=";
        r0 = r1.append(r0);
        r0.append(r9);
    L_0x0080:
        r0 = com.taobao.applink.TBAppLinkSDK.getInstance();
        r0 = r0.sOpenParam;
        r0 = r0.mTtid;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x009f;
    L_0x008e:
        r0 = "&ttid=";
        r0 = r1.append(r0);
        r3 = com.taobao.applink.TBAppLinkSDK.getInstance();
        r3 = r3.sOpenParam;
        r3 = r3.mTtid;
        r0.append(r3);
    L_0x009f:
        r0 = "flush content";
        r3 = r1.toString();
        android.util.Log.d(r0, r3);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r8);
        r3 = "?";
        r0 = r0.append(r3);
        r3 = r1.toString();
        r0 = r0.append(r3);
        r0 = r0.toString();
        r3 = new java.net.URL;	 Catch:{ Throwable -> 0x0140, all -> 0x0162 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x0140, all -> 0x0162 }
        r0 = r3.openConnection();	 Catch:{ Throwable -> 0x0140, all -> 0x0162 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Throwable -> 0x0140, all -> 0x0162 }
        r3 = "POST";
        r0.setRequestMethod(r3);	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r3 = 1;
        r0.setDoOutput(r3);	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r3 = 1;
        r0.setDoInput(r3);	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r3 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setConnectTimeout(r3);	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r3 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setReadTimeout(r3);	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r2 = r0.getOutputStream();	 Catch:{ Throwable -> 0x0181, all -> 0x0177 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r3 = "UTF-8";
        r1 = java.net.URLEncoder.encode(r1, r3);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = r1.getBytes();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r2.write(r1);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r3 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r4 = r0.getInputStream();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r3.<init>(r1);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = "";
    L_0x010a:
        r4 = r3.readLine();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        if (r4 == 0) goto L_0x0128;
    L_0x0110:
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r5.<init>();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = r5.append(r1);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r5 = "\n";
        r1 = r1.append(r5);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = r1.append(r4);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        goto L_0x010a;
    L_0x0128:
        r3 = "flush response";
        android.util.Log.d(r3, r1);	 Catch:{ Throwable -> 0x0187, all -> 0x0177 }
        if (r0 == 0) goto L_0x0132;
    L_0x012f:
        r0.disconnect();
    L_0x0132:
        if (r2 == 0) goto L_0x013a;
    L_0x0134:
        r2.flush();	 Catch:{ IOException -> 0x013b }
        r2.close();	 Catch:{ IOException -> 0x013b }
    L_0x013a:
        return;
    L_0x013b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x013a;
    L_0x0140:
        r0 = move-exception;
        r1 = r2;
    L_0x0142:
        r3 = "flush exception";
        r0 = r0.getStackTrace();	 Catch:{ all -> 0x017c }
        r0 = r0.toString();	 Catch:{ all -> 0x017c }
        android.util.Log.d(r3, r0);	 Catch:{ all -> 0x017c }
        if (r2 == 0) goto L_0x0154;
    L_0x0151:
        r2.disconnect();
    L_0x0154:
        if (r1 == 0) goto L_0x013a;
    L_0x0156:
        r1.flush();	 Catch:{ IOException -> 0x015d }
        r1.close();	 Catch:{ IOException -> 0x015d }
        goto L_0x013a;
    L_0x015d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x013a;
    L_0x0162:
        r0 = move-exception;
        r1 = r2;
    L_0x0164:
        if (r1 == 0) goto L_0x0169;
    L_0x0166:
        r1.disconnect();
    L_0x0169:
        if (r2 == 0) goto L_0x0171;
    L_0x016b:
        r2.flush();	 Catch:{ IOException -> 0x0172 }
        r2.close();	 Catch:{ IOException -> 0x0172 }
    L_0x0171:
        throw r0;
    L_0x0172:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0171;
    L_0x0177:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0164;
    L_0x017c:
        r0 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x0164;
    L_0x0181:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0142;
    L_0x0187:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0142;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.applink.c.b.b(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void a(a aVar) {
        try {
            new c(this, aVar).execute(new Void[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, String str3) {
        if (!e.a(str)) {
            synchronized (this) {
                try {
                    new d(this, str, str2, str3).execute(new Void[0]);
                } catch (Exception e) {
                    Log.d("flush exception", e.getStackTrace().toString());
                }
            }
        }
    }

    public boolean a(String str) {
        return this.d.containsKey(str) ? ((Long) this.d.get(str)).longValue() - System.currentTimeMillis() < 1000 : true;
    }

    public a b() {
        if (a(this.b)) {
            a(null);
        }
        return this.e;
    }
}
