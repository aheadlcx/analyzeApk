package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

public class s extends a {
    private Context a = null;
    private String b = null;
    private String c = null;

    public s(Context context) {
        super("uuid");
        this.a = context;
        this.b = null;
        this.c = null;
    }

    public String f() {
        try {
            if (!(TextUtils.isEmpty(a("ro.yunos.version", "")) || this.a == null)) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.a);
                if (sharedPreferences != null) {
                    String string = sharedPreferences.getString("yosuid", "");
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                    this.c = b("23346339");
                    if (!(TextUtils.isEmpty(this.c) || this.a == null || sharedPreferences == null)) {
                        Editor edit = sharedPreferences.edit();
                        if (edit != null) {
                            edit.putString("yosuid", this.c).commit();
                        }
                    }
                    return this.c;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r9) {
        /*
        r8 = this;
        r2 = 0;
        r0 = "ro.yunos.openuuid";
        r1 = "";
        r0 = a(r0, r1);
        r8.c = r0;
        r0 = r8.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x0016;
    L_0x0013:
        r0 = r8.c;
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = "ro.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r8.b = r0;
        r0 = r8.b;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0028:
        r0 = "ro.sys.aliyun.clouduuid";
        r1 = "";
        r0 = a(r0, r1);
        r8.b = r0;
    L_0x0032:
        r0 = r8.b;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x00ea;
    L_0x003a:
        r3 = 0;
        r5 = 0;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x00f8, all -> 0x0110 }
        r1 = "https://cmnsguider.yunos.com:443/genDeviceToken";
        r0.<init>(r1);	 Catch:{ Exception -> 0x00f8, all -> 0x0110 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x00f8, all -> 0x0110 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x00f8, all -> 0x0110 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = "Content-Type";
        r4 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r1, r4);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = new com.umeng.commonsdk.statistics.idtracking.t;	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1.<init>(r8);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r0.setHostnameVerifier(r1);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1.<init>();	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4 = "appKey=";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4 = "23338940";
        r6 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r6);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4 = "&uuid=";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4 = "FC1FE84794417B1BEF276234F6FB4E63";
        r6 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r6);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r6 = r0.getOutputStream();	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4.<init>(r6);	 Catch:{ Exception -> 0x0153, all -> 0x013b }
        r4.writeBytes(r1);	 Catch:{ Exception -> 0x0157, all -> 0x0142 }
        r4.flush();	 Catch:{ Exception -> 0x0157, all -> 0x0142 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0157, all -> 0x0142 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r3) goto L_0x0164;
    L_0x00b7:
        r3 = r0.getInputStream();	 Catch:{ Exception -> 0x015b, all -> 0x0142 }
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x015f, all -> 0x0148 }
        r5 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x015f, all -> 0x0148 }
        r5.<init>(r3);	 Catch:{ Exception -> 0x015f, all -> 0x0148 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x015f, all -> 0x0148 }
        r2 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
        r2.<init>();	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
    L_0x00ca:
        r5 = r1.readLine();	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
        if (r5 == 0) goto L_0x00ee;
    L_0x00d0:
        r2.append(r5);	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
        goto L_0x00ca;
    L_0x00d4:
        r2 = move-exception;
        r2 = r3;
    L_0x00d6:
        if (r4 == 0) goto L_0x00db;
    L_0x00d8:
        r4.close();	 Catch:{ Exception -> 0x0129 }
    L_0x00db:
        if (r1 == 0) goto L_0x00e0;
    L_0x00dd:
        r1.close();	 Catch:{ Exception -> 0x012b }
    L_0x00e0:
        if (r2 == 0) goto L_0x00e5;
    L_0x00e2:
        r2.close();	 Catch:{ Exception -> 0x012d }
    L_0x00e5:
        if (r0 == 0) goto L_0x00ea;
    L_0x00e7:
        r0.disconnect();
    L_0x00ea:
        r0 = r8.c;
        goto L_0x0015;
    L_0x00ee:
        if (r2 == 0) goto L_0x00f6;
    L_0x00f0:
        r2 = r2.toString();	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
        r8.c = r2;	 Catch:{ Exception -> 0x00d4, all -> 0x014d }
    L_0x00f6:
        r2 = r3;
        goto L_0x00d6;
    L_0x00f8:
        r0 = move-exception;
        r0 = r2;
        r1 = r2;
    L_0x00fb:
        if (r0 == 0) goto L_0x0100;
    L_0x00fd:
        r0.close();	 Catch:{ Exception -> 0x012f }
    L_0x0100:
        if (r2 == 0) goto L_0x0105;
    L_0x0102:
        r5.close();	 Catch:{ Exception -> 0x0131 }
    L_0x0105:
        if (r2 == 0) goto L_0x010a;
    L_0x0107:
        r3.close();	 Catch:{ Exception -> 0x0133 }
    L_0x010a:
        if (r1 == 0) goto L_0x00ea;
    L_0x010c:
        r1.disconnect();
        goto L_0x00ea;
    L_0x0110:
        r0 = move-exception;
        r4 = r2;
        r3 = r2;
        r1 = r2;
    L_0x0114:
        if (r4 == 0) goto L_0x0119;
    L_0x0116:
        r4.close();	 Catch:{ Exception -> 0x0135 }
    L_0x0119:
        if (r2 == 0) goto L_0x011e;
    L_0x011b:
        r2.close();	 Catch:{ Exception -> 0x0137 }
    L_0x011e:
        if (r3 == 0) goto L_0x0123;
    L_0x0120:
        r3.close();	 Catch:{ Exception -> 0x0139 }
    L_0x0123:
        if (r1 == 0) goto L_0x0128;
    L_0x0125:
        r1.disconnect();
    L_0x0128:
        throw r0;
    L_0x0129:
        r3 = move-exception;
        goto L_0x00db;
    L_0x012b:
        r1 = move-exception;
        goto L_0x00e0;
    L_0x012d:
        r1 = move-exception;
        goto L_0x00e5;
    L_0x012f:
        r0 = move-exception;
        goto L_0x0100;
    L_0x0131:
        r0 = move-exception;
        goto L_0x0105;
    L_0x0133:
        r0 = move-exception;
        goto L_0x010a;
    L_0x0135:
        r4 = move-exception;
        goto L_0x0119;
    L_0x0137:
        r2 = move-exception;
        goto L_0x011e;
    L_0x0139:
        r2 = move-exception;
        goto L_0x0123;
    L_0x013b:
        r1 = move-exception;
        r4 = r2;
        r3 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0114;
    L_0x0142:
        r1 = move-exception;
        r3 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0114;
    L_0x0148:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0114;
    L_0x014d:
        r2 = move-exception;
        r7 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0114;
    L_0x0153:
        r1 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x00fb;
    L_0x0157:
        r1 = move-exception;
        r1 = r0;
        r0 = r4;
        goto L_0x00fb;
    L_0x015b:
        r1 = move-exception;
        r1 = r2;
        goto L_0x00d6;
    L_0x015f:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00d6;
    L_0x0164:
        r1 = r2;
        goto L_0x00d6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.s.b(java.lang.String):java.lang.String");
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }
}
