package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.d.m;

public class p extends a {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b = null;
    private String c = null;
    private String d = null;

    public p(Context context) {
        super(a);
        this.b = context;
        this.c = null;
        this.d = null;
    }

    public String f() {
        try {
            if (!(TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null)) {
                SharedPreferences a = m.a(this.b);
                if (a != null) {
                    String string = a.getString(e, "");
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                    this.d = b(f);
                    if (!(TextUtils.isEmpty(this.d) || this.b == null || a == null)) {
                        Editor edit = a.edit();
                        if (edit != null) {
                            edit.putString(e, this.d).commit();
                        }
                    }
                    return this.d;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r8) {
        /*
        r7 = this;
        r3 = 0;
        r0 = "ro.yunos.openuuid";
        r1 = "";
        r0 = a(r0, r1);
        r7.d = r0;
        r0 = r7.d;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0018;
    L_0x0015:
        r0 = r7.d;
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = "ro.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r7.c = r0;
        r0 = r7.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0038;
    L_0x002c:
        r0 = "ro.sys.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r7.c = r0;
    L_0x0038:
        r0 = r7.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x00fb;
    L_0x0040:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0118, all -> 0x0143 }
        r1 = "https://cmnsguider.yunos.com:443/genDeviceToken";
        r0.<init>(r1);	 Catch:{ Exception -> 0x0118, all -> 0x0143 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0118, all -> 0x0143 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x0118, all -> 0x0143 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = "Content-Type";
        r2 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = new com.umeng.analytics.c.p$1;	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1.<init>(r7);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r0.setHostnameVerifier(r1);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1.<init>();	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r2 = "appKey=";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r2 = "23338940";
        r4 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r2, r4);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r2 = "&uuid=";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r2 = "FC1FE84794417B1BEF276234F6FB4E63";
        r4 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r2, r4);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r1 = r1.toString();	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r5 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r5.<init>(r2);	 Catch:{ Exception -> 0x018f, all -> 0x016b }
        r5.writeBytes(r1);	 Catch:{ Exception -> 0x0195, all -> 0x0172 }
        r5.flush();	 Catch:{ Exception -> 0x0195, all -> 0x0172 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0195, all -> 0x0172 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r2) goto L_0x01ad;
    L_0x00c5:
        r4 = r0.getInputStream();	 Catch:{ Exception -> 0x01a4, all -> 0x0172 }
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x01a8, all -> 0x0178 }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x01a8, all -> 0x0178 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x01a8, all -> 0x0178 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x01a8, all -> 0x0178 }
        r1 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
        r1.<init>();	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
    L_0x00d8:
        r3 = r2.readLine();	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
        if (r3 == 0) goto L_0x00ff;
    L_0x00de:
        r1.append(r3);	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
        goto L_0x00d8;
    L_0x00e2:
        r1 = move-exception;
        r3 = r4;
    L_0x00e4:
        r1.printStackTrace();	 Catch:{ Exception -> 0x019b, all -> 0x0183 }
    L_0x00e7:
        if (r5 == 0) goto L_0x00ec;
    L_0x00e9:
        r5.close();	 Catch:{ Exception -> 0x0109 }
    L_0x00ec:
        if (r2 == 0) goto L_0x00f1;
    L_0x00ee:
        r2.close();	 Catch:{ Exception -> 0x010e }
    L_0x00f1:
        if (r3 == 0) goto L_0x00f6;
    L_0x00f3:
        r3.close();	 Catch:{ Exception -> 0x0113 }
    L_0x00f6:
        if (r0 == 0) goto L_0x00fb;
    L_0x00f8:
        r0.disconnect();
    L_0x00fb:
        r0 = r7.d;
        goto L_0x0017;
    L_0x00ff:
        if (r1 == 0) goto L_0x0107;
    L_0x0101:
        r1 = r1.toString();	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
        r7.d = r1;	 Catch:{ Exception -> 0x00e2, all -> 0x017d }
    L_0x0107:
        r3 = r4;
        goto L_0x00e7;
    L_0x0109:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00ec;
    L_0x010e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00f1;
    L_0x0113:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00f6;
    L_0x0118:
        r0 = move-exception;
        r1 = r3;
        r2 = r3;
        r4 = r3;
    L_0x011c:
        r0.printStackTrace();	 Catch:{ all -> 0x018a }
        if (r1 == 0) goto L_0x0124;
    L_0x0121:
        r1.close();	 Catch:{ Exception -> 0x0134 }
    L_0x0124:
        if (r3 == 0) goto L_0x0129;
    L_0x0126:
        r3.close();	 Catch:{ Exception -> 0x0139 }
    L_0x0129:
        if (r2 == 0) goto L_0x012e;
    L_0x012b:
        r2.close();	 Catch:{ Exception -> 0x013e }
    L_0x012e:
        if (r4 == 0) goto L_0x00fb;
    L_0x0130:
        r4.disconnect();
        goto L_0x00fb;
    L_0x0134:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0124;
    L_0x0139:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0129;
    L_0x013e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x012e;
    L_0x0143:
        r0 = move-exception;
        r5 = r3;
        r4 = r3;
        r1 = r3;
    L_0x0147:
        if (r5 == 0) goto L_0x014c;
    L_0x0149:
        r5.close();	 Catch:{ Exception -> 0x015c }
    L_0x014c:
        if (r3 == 0) goto L_0x0151;
    L_0x014e:
        r3.close();	 Catch:{ Exception -> 0x0161 }
    L_0x0151:
        if (r4 == 0) goto L_0x0156;
    L_0x0153:
        r4.close();	 Catch:{ Exception -> 0x0166 }
    L_0x0156:
        if (r1 == 0) goto L_0x015b;
    L_0x0158:
        r1.disconnect();
    L_0x015b:
        throw r0;
    L_0x015c:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x014c;
    L_0x0161:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0151;
    L_0x0166:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0156;
    L_0x016b:
        r1 = move-exception;
        r5 = r3;
        r4 = r3;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0147;
    L_0x0172:
        r1 = move-exception;
        r4 = r3;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0147;
    L_0x0178:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0147;
    L_0x017d:
        r1 = move-exception;
        r3 = r2;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0147;
    L_0x0183:
        r1 = move-exception;
        r4 = r3;
        r3 = r2;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0147;
    L_0x018a:
        r0 = move-exception;
        r5 = r1;
        r1 = r4;
        r4 = r2;
        goto L_0x0147;
    L_0x018f:
        r1 = move-exception;
        r2 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x011c;
    L_0x0195:
        r1 = move-exception;
        r2 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x011c;
    L_0x019b:
        r1 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r5;
        r6 = r3;
        r3 = r2;
        r2 = r6;
        goto L_0x011c;
    L_0x01a4:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00e4;
    L_0x01a8:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x00e4;
    L_0x01ad:
        r2 = r3;
        goto L_0x00e7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.c.p.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
