package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class an extends y {
    private static final String a = "uuid";
    private static final String e = "yosuid";
    private static final String f = "23346339";
    private Context b = null;
    private String c = null;
    private String d = null;

    public an(Context context) {
        super(a);
        this.b = context;
        this.c = null;
        this.d = null;
    }

    public String f() {
        try {
            if (!(TextUtils.isEmpty(a("ro.yunos.version", "")) || this.b == null)) {
                SharedPreferences a = ba.a(this.b);
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
        if (r0 != 0) goto L_0x0016;
    L_0x0013:
        r0 = r7.d;
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = "ro.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r7.c = r0;
        r0 = r7.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0028:
        r0 = "ro.sys.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r7.c = r0;
    L_0x0032:
        r0 = r7.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x00eb;
    L_0x003a:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0108, all -> 0x0133 }
        r1 = "https://cmnsguider.yunos.com:443/genDeviceToken";
        r0.<init>(r1);	 Catch:{ Exception -> 0x0108, all -> 0x0133 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0108, all -> 0x0133 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x0108, all -> 0x0133 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = "Content-Type";
        r2 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = new com.umeng.analytics.pro.an$1;	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1.<init>(r7);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r0.setHostnameVerifier(r1);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1.<init>();	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r2 = "appKey=";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r2 = "23338940";
        r4 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r2, r4);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r2 = "&uuid=";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r2 = "FC1FE84794417B1BEF276234F6FB4E63";
        r4 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r2, r4);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r1 = r1.toString();	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r5 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r5.<init>(r2);	 Catch:{ Exception -> 0x017f, all -> 0x015b }
        r5.writeBytes(r1);	 Catch:{ Exception -> 0x0185, all -> 0x0162 }
        r5.flush();	 Catch:{ Exception -> 0x0185, all -> 0x0162 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0185, all -> 0x0162 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r2) goto L_0x019d;
    L_0x00b5:
        r4 = r0.getInputStream();	 Catch:{ Exception -> 0x0194, all -> 0x0162 }
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0198, all -> 0x0168 }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0198, all -> 0x0168 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x0198, all -> 0x0168 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0168 }
        r1 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
        r1.<init>();	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
    L_0x00c8:
        r3 = r2.readLine();	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
        if (r3 == 0) goto L_0x00ef;
    L_0x00ce:
        r1.append(r3);	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
        goto L_0x00c8;
    L_0x00d2:
        r1 = move-exception;
        r3 = r4;
    L_0x00d4:
        r1.printStackTrace();	 Catch:{ Exception -> 0x018b, all -> 0x0173 }
    L_0x00d7:
        if (r5 == 0) goto L_0x00dc;
    L_0x00d9:
        r5.close();	 Catch:{ Exception -> 0x00f9 }
    L_0x00dc:
        if (r2 == 0) goto L_0x00e1;
    L_0x00de:
        r2.close();	 Catch:{ Exception -> 0x00fe }
    L_0x00e1:
        if (r3 == 0) goto L_0x00e6;
    L_0x00e3:
        r3.close();	 Catch:{ Exception -> 0x0103 }
    L_0x00e6:
        if (r0 == 0) goto L_0x00eb;
    L_0x00e8:
        r0.disconnect();
    L_0x00eb:
        r0 = r7.d;
        goto L_0x0015;
    L_0x00ef:
        if (r1 == 0) goto L_0x00f7;
    L_0x00f1:
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
        r7.d = r1;	 Catch:{ Exception -> 0x00d2, all -> 0x016d }
    L_0x00f7:
        r3 = r4;
        goto L_0x00d7;
    L_0x00f9:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00dc;
    L_0x00fe:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00e1;
    L_0x0103:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00e6;
    L_0x0108:
        r0 = move-exception;
        r1 = r3;
        r2 = r3;
        r4 = r3;
    L_0x010c:
        r0.printStackTrace();	 Catch:{ all -> 0x017a }
        if (r1 == 0) goto L_0x0114;
    L_0x0111:
        r1.close();	 Catch:{ Exception -> 0x0124 }
    L_0x0114:
        if (r3 == 0) goto L_0x0119;
    L_0x0116:
        r3.close();	 Catch:{ Exception -> 0x0129 }
    L_0x0119:
        if (r2 == 0) goto L_0x011e;
    L_0x011b:
        r2.close();	 Catch:{ Exception -> 0x012e }
    L_0x011e:
        if (r4 == 0) goto L_0x00eb;
    L_0x0120:
        r4.disconnect();
        goto L_0x00eb;
    L_0x0124:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0114;
    L_0x0129:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0119;
    L_0x012e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x011e;
    L_0x0133:
        r0 = move-exception;
        r5 = r3;
        r4 = r3;
        r1 = r3;
    L_0x0137:
        if (r5 == 0) goto L_0x013c;
    L_0x0139:
        r5.close();	 Catch:{ Exception -> 0x014c }
    L_0x013c:
        if (r3 == 0) goto L_0x0141;
    L_0x013e:
        r3.close();	 Catch:{ Exception -> 0x0151 }
    L_0x0141:
        if (r4 == 0) goto L_0x0146;
    L_0x0143:
        r4.close();	 Catch:{ Exception -> 0x0156 }
    L_0x0146:
        if (r1 == 0) goto L_0x014b;
    L_0x0148:
        r1.disconnect();
    L_0x014b:
        throw r0;
    L_0x014c:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x013c;
    L_0x0151:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0141;
    L_0x0156:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0146;
    L_0x015b:
        r1 = move-exception;
        r5 = r3;
        r4 = r3;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0137;
    L_0x0162:
        r1 = move-exception;
        r4 = r3;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0137;
    L_0x0168:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0137;
    L_0x016d:
        r1 = move-exception;
        r3 = r2;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0137;
    L_0x0173:
        r1 = move-exception;
        r4 = r3;
        r3 = r2;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0137;
    L_0x017a:
        r0 = move-exception;
        r5 = r1;
        r1 = r4;
        r4 = r2;
        goto L_0x0137;
    L_0x017f:
        r1 = move-exception;
        r2 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x010c;
    L_0x0185:
        r1 = move-exception;
        r2 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x010c;
    L_0x018b:
        r1 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r5;
        r6 = r3;
        r3 = r2;
        r2 = r6;
        goto L_0x010c;
    L_0x0194:
        r1 = move-exception;
        r2 = r3;
        goto L_0x00d4;
    L_0x0198:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x00d4;
    L_0x019d:
        r2 = r3;
        goto L_0x00d7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.an.b(java.lang.String):java.lang.String");
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
