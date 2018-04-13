package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.b.g;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class b {
    public static boolean a = false;
    private static String b = "lxd";
    private static int c = 1;
    private static boolean d = true;
    private static boolean e = false;
    private static boolean f = true;
    private static int g = 1;
    private static int h = 0;
    private static final String i = new StringBuilder(SocialConstants.PARAM_ACT).append(b).toString();
    private static final String j = ("evn" + b);
    private static final String k = ("esp" + b);
    private static final String l = new StringBuilder(NotificationCompat.CATEGORY_ERROR).append(b).toString();
    private static final String m = new StringBuilder(NotificationCompat.CATEGORY_SYSTEM).append(b).toString();
    private static String n = "";
    private static long o = 0;
    private static String p = "";
    private static Object q = new Object();
    private static Object r = new Object();
    private static Object s = new Object();
    private static boolean t = false;
    private static boolean u = false;
    private static String v = "";
    private static ExecutorService w = Executors.newSingleThreadExecutor();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(android.content.Context r9, java.lang.String r10, org.json.JSONObject r11) {
        /*
        r3 = 0;
        r4 = 1;
        r1 = 2;
        r0 = c;
        if (r0 == r4) goto L_0x0009;
    L_0x0007:
        r0 = r1;
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = "MobileAgentRun";
        r2 = new java.lang.StringBuilder;
        r5 = "run into httppost :";
        r2.<init>(r5);
        r2 = r2.append(r10);
        r5 = "####";
        r2 = r2.append(r5);
        r5 = r9.getClass();
        r5 = r5.getName();
        r2 = r2.append(r5);
        r5 = "###";
        r2 = r2.append(r5);
        r5 = r11.toString();
        r2 = r2.append(r5);
        r2 = r2.toString();
        com.iflytek.cloud.thirdparty.c.a(r0, r2);
        r2 = 0;
        r0 = com.iflytek.cloud.thirdparty.h.h(r9);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = com.iflytek.cloud.thirdparty.h.i(r9);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r6 = "UTF-8";
        r5 = java.net.URLEncoder.encode(r5, r6);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r6 = new java.lang.StringBuilder;	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r7 = java.lang.String.valueOf(r10);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r6.<init>(r7);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r7 = "&appkey=";
        r6 = r6.append(r7);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r0 = r6.append(r0);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r6 = "&channel=";
        r0 = r0.append(r6);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r0 = r0.append(r5);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = "&code=";
        r0 = r0.append(r5);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r0 = r0.append(r5);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r0 = r0.toString();	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = "MobileAgentRun";
        r6 = r11.toString();	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        com.iflytek.cloud.thirdparty.c.a(r5, r6);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = "MobileAgentRun";
        r6 = "post start";
        com.iflytek.cloud.thirdparty.c.a(r5, r6);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5 = new java.net.URL;	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r5.<init>(r0);	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r0 = r5.openConnection();	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ ClientProtocolException -> 0x0171, IOException -> 0x0186, JSONException -> 0x019c, Exception -> 0x01b3 }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setReadTimeout(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = 1;
        r0.setDoOutput(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = 1;
        r0.setDoInput(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = 0;
        r0.setUseCaches(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = "POST";
        r0.setRequestMethod(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = com.iflytek.cloud.thirdparty.g.a();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5 = r11.toString();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = r2.a(r5);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5 = "Content-length";
        r6 = new java.lang.StringBuilder;	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r6.<init>();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r7 = r2.length;	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r6 = r6.append(r7);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r6 = r6.toString();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r0.setRequestProperty(r5, r6);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5 = "Content-Type";
        r6 = "application/octet-stream";
        r0.setRequestProperty(r5, r6);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5 = "Charset";
        r6 = "UTF-8";
        r0.setRequestProperty(r5, r6);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5 = r0.getOutputStream();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5.write(r2);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r5.close();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r3 = r0.getResponseCode();	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = "MobileAgentRun";
        r5 = "post end";
        com.iflytek.cloud.thirdparty.c.a(r2, r5);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01d7, all -> 0x01d3 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r3) goto L_0x010d;
    L_0x0103:
        r2 = "MobileAgent";
        r5 = "send success";
        com.iflytek.cloud.thirdparty.c.a(r2, r5);	 Catch:{ ClientProtocolException -> 0x01ea, IOException -> 0x01e5, JSONException -> 0x01e1, Exception -> 0x01dc, all -> 0x01d3 }
        r1 = r4;
    L_0x010d:
        if (r0 == 0) goto L_0x0112;
    L_0x010f:
        r0.disconnect();
    L_0x0112:
        r0 = r1;
    L_0x0113:
        r1 = "MobileAgentHttpPost";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01d0 }
        r4 = "code:";
        r2.<init>(r4);	 Catch:{ Exception -> 0x01d0 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x01d0 }
        r3 = "status:  ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x01d0 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x01d0 }
        r3 = "  content: ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x01d0 }
        r3 = "logJsonAry";
        r3 = r11.getString(r3);	 Catch:{ Exception -> 0x01d0 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x01d0 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x01d0 }
        com.iflytek.cloud.thirdparty.c.a(r1, r2);	 Catch:{ Exception -> 0x01d0 }
    L_0x0146:
        r1 = "MobileAgentRun";
        r2 = new java.lang.StringBuilder;
        r3 = "run out httppost :";
        r2.<init>(r3);
        r3 = r9.getClass();
        r3 = r3.getName();
        r2 = r2.append(r3);
        r3 = " resultcode:";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.iflytek.cloud.thirdparty.c.a(r1, r2);
        goto L_0x0008;
    L_0x0171:
        r0 = move-exception;
    L_0x0172:
        r4 = "MobileAgentRun";
        r5 = r0.toString();	 Catch:{ all -> 0x01c9 }
        com.iflytek.cloud.thirdparty.c.a(r4, r5);	 Catch:{ all -> 0x01c9 }
        r0.printStackTrace();	 Catch:{ all -> 0x01c9 }
        if (r2 == 0) goto L_0x0112;
    L_0x0181:
        r2.disconnect();
        r0 = r1;
        goto L_0x0113;
    L_0x0186:
        r0 = move-exception;
    L_0x0187:
        r4 = "MobileAgentRun";
        r5 = r0.toString();	 Catch:{ all -> 0x01c9 }
        com.iflytek.cloud.thirdparty.c.a(r4, r5);	 Catch:{ all -> 0x01c9 }
        r0.printStackTrace();	 Catch:{ all -> 0x01c9 }
        if (r2 == 0) goto L_0x0112;
    L_0x0196:
        r2.disconnect();
        r0 = r1;
        goto L_0x0113;
    L_0x019c:
        r0 = move-exception;
    L_0x019d:
        r1 = "MobileAgentRun";
        r4 = r0.toString();	 Catch:{ all -> 0x01c9 }
        com.iflytek.cloud.thirdparty.c.a(r1, r4);	 Catch:{ all -> 0x01c9 }
        r0.printStackTrace();	 Catch:{ all -> 0x01c9 }
        r1 = 3;
        if (r2 == 0) goto L_0x0112;
    L_0x01ad:
        r2.disconnect();
        r0 = r1;
        goto L_0x0113;
    L_0x01b3:
        r0 = move-exception;
    L_0x01b4:
        r4 = "MobileAgentRun";
        r5 = r0.toString();	 Catch:{ all -> 0x01c9 }
        com.iflytek.cloud.thirdparty.c.a(r4, r5);	 Catch:{ all -> 0x01c9 }
        r0.printStackTrace();	 Catch:{ all -> 0x01c9 }
        if (r2 == 0) goto L_0x0112;
    L_0x01c3:
        r2.disconnect();
        r0 = r1;
        goto L_0x0113;
    L_0x01c9:
        r0 = move-exception;
    L_0x01ca:
        if (r2 == 0) goto L_0x01cf;
    L_0x01cc:
        r2.disconnect();
    L_0x01cf:
        throw r0;
    L_0x01d0:
        r1 = move-exception;
        goto L_0x0146;
    L_0x01d3:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x01ca;
    L_0x01d7:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x01b4;
    L_0x01dc:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r4;
        goto L_0x01b4;
    L_0x01e1:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x019d;
    L_0x01e5:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x0187;
    L_0x01ea:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x0172;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.b.a(android.content.Context, java.lang.String, org.json.JSONObject):int");
    }

    protected static SharedPreferences a(Context context) {
        return context.getSharedPreferences(b + "MoblieAgent_event_" + context.getPackageName(), 0);
    }

    private static String a(Context context, String str, SharedPreferences sharedPreferences) {
        long j = 0;
        n = "";
        String a = h.a(context, str);
        Editor edit = sharedPreferences.edit();
        edit.putString(WBConstants.SSO_APP_KEY, str);
        edit.putString(Parameters.SESSION_ID, a);
        edit.putLong("lastResumeTime", System.currentTimeMillis());
        edit.putString("activities", i(context, "onResume", null));
        long j2 = sharedPreferences.getLong("readFlowRev", 0);
        long[] o = h.o(context);
        edit.putLong("readFlowRev", o[0]);
        j2 = o[0] - j2;
        if (j2 < 0) {
            j2 = 0;
        }
        edit.putLong("consumeFlowRev", j2);
        j2 = sharedPreferences.getLong("readFlowSnd", 0);
        edit.putLong("readFlowSnd", o[1]);
        j2 = o[1] - j2;
        if (j2 >= 0) {
            j = j2;
        }
        edit.putLong("consumeFlowSnd", j);
        edit.commit();
        p = a;
        return a;
    }

    private static void a(Context context, String str, long j) {
        synchronized (q) {
            SharedPreferences c = c(context);
            int i = c.getInt("uploadcount", 0);
            String str2 = c.getString("uploadList", "") + str + "|";
            if (j > 10000) {
                j = 1;
            }
            c.edit().putString("uploadList", str2).commit();
            c.edit().putLong("uploadpopindex", j).commit();
            if (str2.split("\\|").length > BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) {
                String n = n(context);
                context.deleteFile(n);
                j(context, n);
            } else {
                c.edit().putInt("uploadcount", i + 1).commit();
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        if (c == 0) {
            Log.i("MobileAgent", "Du have not permission to collect data");
        } else if (str.contains("#")) {
            Log.w("MobileAgent", "the eventId contain illegal char");
        } else {
            h(context, str, str2);
        }
    }

    protected static void a(Context context, boolean z) {
        if (z) {
            c.a("MobileAgentRun", "run into pageact onresumep");
            h(context);
        } else {
            c.a("MobileAgentRun", "run into pageact onpausep");
            i(context);
        }
        c.a("MobileAgentRun", "run out pageact");
    }

    protected static boolean a(Context context, int i) {
        int i2;
        int i3;
        SharedPreferences k = k(context);
        if (i == 3) {
            i2 = k.getInt("actionmonth", 0);
            i3 = k.getInt("actionday", 0);
        } else if (i == 2) {
            i2 = k.getInt("eventmonth", 0);
            i3 = k.getInt("eventday", 0);
        } else {
            i2 = k.getInt("sysmonth", 0);
            i3 = k.getInt("sysday", 0);
        }
        Date date = new Date();
        return (Integer.valueOf(new SimpleDateFormat("M").format(date)).intValue() == i2 && Integer.valueOf(new SimpleDateFormat("dd").format(date)).intValue() == i3) ? false : true;
    }

    private static boolean a(Context context, SharedPreferences sharedPreferences) {
        SharedPreferences a = a(context);
        String string = a.getString("eventlogs", "");
        if (string.equals("")) {
            return false;
        }
        String string2 = l(context).getString(Parameters.SESSION_ID, null);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SpeechConstant.IST_SESSION_ID, string2);
            jSONObject.put("logJsonAry", string);
            if (a(context, jSONObject.toString(), 2)) {
                a.edit().putString("eventlogs", "").commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static boolean a(Context context, SharedPreferences sharedPreferences, boolean z) {
        String string = sharedPreferences.getString(Parameters.SESSION_ID, "");
        String string2 = sharedPreferences.getString("activities", "");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SpeechConstant.IST_SESSION_ID, string);
            jSONObject.put("logs", string2);
            if (z) {
                jSONObject.put("flowConsumpRev", sharedPreferences.getLong("consumeFlowRev", 0));
                jSONObject.put("flowConsumpSnd", sharedPreferences.getLong("consumeFlowSnd", 0));
            } else {
                jSONObject.put("flowConsumpRev", 0);
                jSONObject.put("flowConsumpSnd", 0);
            }
            if (a(context, jSONObject.toString(), 3)) {
                sharedPreferences.edit().putString("activities", "").commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected static boolean a(Context context, String str) {
        String h = h(context, str);
        if (h.equals("")) {
            b(context, 3);
            i(context, str);
            return true;
        }
        int a;
        String str2 = "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:postactlog";
        JSONObject d = d(context);
        try {
            d.put(SpeechConstant.IST_SESSION_ID, new JSONObject(h).get(SpeechConstant.IST_SESSION_ID));
        } catch (JSONException e) {
            d.put(SpeechConstant.IST_SESSION_ID, "");
        }
        try {
            d.put("mac", h.g(context));
            try {
                d.put("deviceDetail", URLEncoder.encode(h.a(), "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                d.put("deviceDetail", "");
            }
            try {
                d.put("manufacturer", URLEncoder.encode(h.b(), "UTF-8"));
            } catch (UnsupportedEncodingException e3) {
                d.put("manufacturer", "");
            }
            try {
                d.put("phoneOs", URLEncoder.encode(h.c(), "UTF-8"));
            } catch (UnsupportedEncodingException e4) {
                d.put("phoneOs", "");
            }
            try {
                d.put("accessPoint", URLEncoder.encode(h.l(context), "UTF-8"));
            } catch (UnsupportedEncodingException e5) {
                d.put("accessPoint", "");
            }
            try {
                d.put("netWorkType", URLEncoder.encode(h.p(context), "UTF-8"));
            } catch (UnsupportedEncodingException e6) {
                d.put("netWorkType", "");
            }
            d.put("uuid", v);
            d.put("deviceId", h.a(context));
            d.put("cpuRatioMax", h.f());
            d.put("cpuRatioCur", h.g());
            d.put("menoryRatio", h.h());
            d.put("logJsonAry", new JSONArray("[" + h + "]"));
            a = a(context, str2, d);
        } catch (JSONException e7) {
            e7.printStackTrace();
            a = 3;
        }
        if (a != 1 && a != 3) {
            return a == 2 ? false : false;
        } else {
            b(context, 3);
            i(context, str);
            Log.i("MobileAgent", "act log sd");
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized boolean a(android.content.Context r10, java.lang.String r11, int r12) {
        /*
        r1 = 1;
        r0 = 0;
        r3 = com.iflytek.cloud.thirdparty.b.class;
        monitor-enter(r3);
        r2 = 3;
        if (r12 != r2) goto L_0x0047;
    L_0x0008:
        r2 = i;	 Catch:{ all -> 0x0074 }
    L_0x000a:
        r4 = r11.trim();	 Catch:{ all -> 0x0074 }
        r5 = "";
        r4 = r4.equals(r5);	 Catch:{ all -> 0x0074 }
        if (r4 != 0) goto L_0x0045;
    L_0x0017:
        r4 = m(r10);	 Catch:{ all -> 0x0074 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0074 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0074 }
        r6.<init>(r2);	 Catch:{ all -> 0x0074 }
        r8 = 1;
        r8 = r8 + r4;
        r2 = r6.append(r4);	 Catch:{ all -> 0x0074 }
        r4 = r2.toString();	 Catch:{ all -> 0x0074 }
        r2 = 0;
        r5 = 1;
        r2 = r10.openFileOutput(r4, r5);	 Catch:{ FileNotFoundException -> 0x005e, IOException -> 0x0077 }
        a(r10, r4, r8);	 Catch:{ FileNotFoundException -> 0x00a0, IOException -> 0x0077 }
        r4 = r11.getBytes();	 Catch:{ FileNotFoundException -> 0x00a0, IOException -> 0x0077 }
        r2.write(r4);	 Catch:{ FileNotFoundException -> 0x00a0, IOException -> 0x0077 }
        if (r2 == 0) goto L_0x0044;
    L_0x0041:
        r2.close();	 Catch:{ IOException -> 0x0098 }
    L_0x0044:
        r0 = r1;
    L_0x0045:
        monitor-exit(r3);
        return r0;
    L_0x0047:
        r2 = 2;
        if (r12 != r2) goto L_0x004d;
    L_0x004a:
        r2 = j;	 Catch:{ all -> 0x0074 }
        goto L_0x000a;
    L_0x004d:
        r2 = 4;
        if (r12 != r2) goto L_0x0053;
    L_0x0050:
        r2 = l;	 Catch:{ all -> 0x0074 }
        goto L_0x000a;
    L_0x0053:
        if (r12 != r1) goto L_0x0058;
    L_0x0055:
        r2 = m;	 Catch:{ all -> 0x0074 }
        goto L_0x000a;
    L_0x0058:
        r2 = 5;
        if (r12 != r2) goto L_0x0045;
    L_0x005b:
        r2 = k;	 Catch:{ all -> 0x0074 }
        goto L_0x000a;
    L_0x005e:
        r1 = move-exception;
        r1 = r2;
    L_0x0060:
        r2 = "MobileAgentRun";
        r4 = "can not find log file";
        android.util.Log.w(r2, r4);	 Catch:{ all -> 0x009d }
        if (r1 == 0) goto L_0x0045;
    L_0x006b:
        r1.close();	 Catch:{ IOException -> 0x006f }
        goto L_0x0045;
    L_0x006f:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0074 }
        goto L_0x0045;
    L_0x0074:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0077:
        r0 = move-exception;
        r0 = "MobileAgentRun";
        r4 = "file r/w execption";
        android.util.Log.w(r0, r4);	 Catch:{ all -> 0x008c }
        if (r2 == 0) goto L_0x0044;
    L_0x0083:
        r2.close();	 Catch:{ IOException -> 0x0087 }
        goto L_0x0044;
    L_0x0087:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0074 }
        goto L_0x0044;
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        if (r2 == 0) goto L_0x0092;
    L_0x008f:
        r2.close();	 Catch:{ IOException -> 0x0093 }
    L_0x0092:
        throw r0;	 Catch:{ all -> 0x0074 }
    L_0x0093:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0074 }
        goto L_0x0092;
    L_0x0098:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0074 }
        goto L_0x0044;
    L_0x009d:
        r0 = move-exception;
        r2 = r1;
        goto L_0x008d;
    L_0x00a0:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.b.a(android.content.Context, java.lang.String, int):boolean");
    }

    private static boolean a(SharedPreferences sharedPreferences) {
        return System.currentTimeMillis() - sharedPreferences.getLong("endTime", -1) > 30000;
    }

    protected static SharedPreferences b(Context context) {
        return context.getSharedPreferences(b + "MoblieAgent_event_sp" + context.getPackageName(), 0);
    }

    protected static void b(Context context, int i) {
        Date date = new Date();
        int parseInt = Integer.parseInt(new SimpleDateFormat("dd").format(date));
        int parseInt2 = Integer.parseInt(new SimpleDateFormat("M").format(date));
        Editor edit = k(context).edit();
        if (i == 3) {
            edit.putInt("actionmonth", parseInt2);
            edit.putInt("actionday", parseInt);
        } else if (i == 2) {
            edit.putInt("eventmonth", parseInt2);
            edit.putInt("eventday", parseInt);
        } else {
            edit.putInt("sysmonth", parseInt2);
            edit.putInt("sysday", parseInt);
        }
        edit.commit();
    }

    protected static void b(Context context, String str, String str2) {
        synchronized (r) {
            String a = h.a(str);
            String a2 = h.a(str2);
            SharedPreferences a3 = a(context);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a3.getString("eventlogs", ""));
            try {
                stringBuffer.append(URLEncoder.encode(a, "UTF-8"));
                stringBuffer.append("|");
                stringBuffer.append(URLEncoder.encode(a2, "UTF-8"));
                stringBuffer.append("|");
                stringBuffer.append(1);
                stringBuffer.append("|");
                stringBuffer.append(System.currentTimeMillis());
                stringBuffer.append("\n");
                a3.edit().putString("eventlogs", stringBuffer.toString()).commit();
                a(context, null);
            } catch (UnsupportedEncodingException e) {
                c.a("MobileAgentRun", "unsupport utf-8,can't onEvent()");
                return;
            }
        }
        b(context, false);
    }

    protected static void b(Context context, boolean z) {
        if (c == 1) {
            c.a("MobileAgentRun", "run into strategy");
            f(context);
            if (!e || (e && o(context))) {
                switch (g) {
                    case 1:
                        new d(context, 6).start();
                        break;
                    case 2:
                        if (z) {
                            new d(context, 6).start();
                            f = false;
                            break;
                        }
                        break;
                    case 3:
                        if (a(context, 3)) {
                            new d(context, 6).start();
                            break;
                        }
                        break;
                }
            }
            f = false;
            c.a("MobileAgentRun", "run out strategy");
        }
    }

    private static boolean b(Context context, SharedPreferences sharedPreferences) {
        SharedPreferences b = b(context);
        String string = b.getString("eventlogs", "");
        if (string.equals("")) {
            return false;
        }
        String string2 = l(context).getString(Parameters.SESSION_ID, null);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SpeechConstant.IST_SESSION_ID, string2);
            jSONObject.put("logJsonAry", string);
            if (a(context, jSONObject.toString(), 5)) {
                b.edit().putString("eventlogs", "").commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected static boolean b(Context context, String str) {
        try {
            if (a(context, "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:postsyslog", new JSONObject(h(context, str))) == 1) {
                i(context, str);
                return true;
            }
        } catch (JSONException e) {
            i(context, str);
            Log.i("MobileAgent", "SDK del a dirty data");
        }
        return false;
    }

    protected static SharedPreferences c(Context context) {
        return context.getSharedPreferences(b + "MoblieAgent_upload_" + context.getPackageName(), 0);
    }

    protected static void c(Context context, String str, String str2) {
        synchronized (s) {
            String a = h.a(str);
            String a2 = h.a(str2);
            SharedPreferences b = b(context);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(b.getString("eventlogs", ""));
            try {
                stringBuffer.append(URLEncoder.encode(a, "UTF-8"));
                stringBuffer.append("|");
                stringBuffer.append(URLEncoder.encode(a2, "UTF-8"));
                stringBuffer.append("|");
                stringBuffer.append(1);
                stringBuffer.append("|");
                stringBuffer.append(System.currentTimeMillis());
                stringBuffer.append("\n");
                b.edit().putString("eventlogs", stringBuffer.toString()).commit();
                if (g == 1 || stringBuffer.toString().getBytes().length > 10000) {
                    b(context, null);
                }
            } catch (UnsupportedEncodingException e) {
                c.a("MobileAgent", "unsupport utf-8,can't onEvent()");
                return;
            }
        }
        b(context, false);
    }

    protected static boolean c(Context context, String str) {
        String h = h(context, str);
        try {
            JSONObject jSONObject = new JSONObject(h);
            jSONObject.put("pid", 1);
            jSONObject.put("protocolVersion", "3.1.4");
            jSONObject.put("sdkVersion", "3.2.0.2");
            jSONObject.put("cid", h.b(context));
            jSONObject.put("deviceId", h.a(context));
            jSONObject.put(WBConstants.SSO_APP_KEY, h.h(context));
            jSONObject.put("packageName", h.j(context));
            jSONObject.put("versionCode", h.m(context));
            jSONObject.put("versionName", h.n(context));
            jSONObject.put("sendTime", System.currentTimeMillis());
            int a = a(context, "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:posterrlog", jSONObject);
            if (a != 1 && a != 3) {
                return a == 2 ? false : false;
            } else {
                b(context, 3);
                i(context, str);
                Log.i("MobileAgent", "erLog sd");
                c.a("MobileAgent", "send errlog success \n" + h);
                return true;
            }
        } catch (JSONException e) {
            i(context, str);
            Log.i("MobileAgent", "SDK del a dirty data");
            return false;
        }
    }

    protected static JSONObject d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pid", 1);
            jSONObject.put("protocolVersion", "3.1.4");
            jSONObject.put("sdkVersion", "3.2.0.2");
            jSONObject.put("cid", h.b(context));
            jSONObject.put(WBConstants.SSO_APP_KEY, h.h(context));
            jSONObject.put("packageName", context.getPackageName());
            jSONObject.put("versionCode", h.m(context));
            jSONObject.put("versionName", h.n(context));
            jSONObject.put("sendTime", System.currentTimeMillis());
            jSONObject.put("deviceId", h.a(context));
            jSONObject.put(g.b, h.i(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    protected static void d(Context context, String str) {
        k(context).edit().putString(str, "record").commit();
    }

    public static void d(Context context, String str, String str2) {
        if (t) {
            Log.i("MobileAgent", "already init");
            return;
        }
        try {
            if (!a) {
                a = !context.getSharedPreferences("MoblieAgent_debug", 0).getString("debug", "").equals("");
            }
        } catch (Exception e) {
        }
        c.a("MobileAgentAPI", "run in init[" + str + "," + str2 + "]");
        if (context == null) {
            Log.e("MobileAgent", "Exception occurent in joinDu ,context cann't be null");
        } else if (TextUtils.isEmpty(str) || h.a(str, 50)) {
            Log.e("MobileAgent", "Exception occurent in joinDu ,appID cann't be null or empty");
        } else {
            if (TextUtils.isEmpty(str2)) {
                str2 = "0";
            } else if (h.a(str2, 100)) {
                str2 = str2.substring(0, 99);
                Log.e("MobileAgent", "Exception occurent in joinDu ,channelID cann't be null or empty");
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(b + "MoblieAgent_sys_config", 0);
            sharedPreferences.edit().putString("MOBILE_APPKEY", str).commit();
            sharedPreferences.edit().putString("MOBILE_CHANNEL", str2).commit();
            if (!e(context, "#lxapkmd5")) {
                new Thread(new f(context)).start();
            }
            t = true;
            Log.i("MobileAgent", "finish init SUCCESS " + a);
        }
    }

    protected static void e(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pid", 1);
            jSONObject.put("protocolVersion", "3.1.4");
            jSONObject.put("sdkVersion", "3.2.0.2");
            jSONObject.put("cid", h.b(context));
            jSONObject.put("deviceId", h.a(context));
            jSONObject.put(WBConstants.SSO_APP_KEY, h.h(context));
            jSONObject.put("packageName", context.getPackageName());
            jSONObject.put("versionCode", h.m(context));
            jSONObject.put("versionName", h.n(context));
            jSONObject.put("sendTime", System.currentTimeMillis());
            jSONObject.put("imsi", h.c(context));
            jSONObject.put("mac", h.g(context));
            jSONObject.put("deviceDetail", URLEncoder.encode(h.a(), "UTF-8"));
            jSONObject.put("manufacturer", URLEncoder.encode(h.b(), "UTF-8"));
            jSONObject.put("phoneOS", URLEncoder.encode(h.c(), "UTF-8"));
            jSONObject.put("screenWidth", h.d(context));
            jSONObject.put("screenHeight", h.e(context));
            jSONObject.put("screenDensity", h.f(context));
            jSONObject.put("carrierName", URLEncoder.encode(h.k(context), "UTF-8"));
            jSONObject.put("accessPoint", h.l(context));
            jSONObject.put("countryCode", h.d());
            jSONObject.put("languageCode", h.e());
            jSONObject.put(g.b, URLEncoder.encode(h.i(context), "UTF-8"));
            if (a(context, jSONObject.toString(), 1)) {
                b(context, 1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    protected static boolean e(Context context, String str) {
        return !k(context).getString(str, "").equals("");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static boolean e(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
        /*
        r6 = 3;
        r1 = 0;
        r2 = 1;
        if (r9 != 0) goto L_0x00ed;
    L_0x0005:
        r0 = h(r7, r8);
    L_0x0009:
        r3 = "";
        r3 = r0.equals(r3);
        if (r3 != 0) goto L_0x013f;
    L_0x0012:
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00fb }
        r3.<init>(r0);	 Catch:{ JSONException -> 0x00fb }
        r0 = com.iflytek.cloud.thirdparty.h.b(r7);	 Catch:{ JSONException -> 0x00fb }
        r4 = "pid";
        r5 = 1;
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00fb }
        r4 = "protocolVersion";
        r5 = "3.1.4";
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00fb }
        r4 = "sdkVersion";
        r5 = "3.2.0.2";
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00fb }
        r4 = "cid";
        r3.put(r4, r0);	 Catch:{ JSONException -> 0x00fb }
        r0 = "appKey";
        r4 = com.iflytek.cloud.thirdparty.h.h(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "packageName";
        r4 = com.iflytek.cloud.thirdparty.h.j(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "versionCode";
        r4 = com.iflytek.cloud.thirdparty.h.m(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "versionName";
        r4 = com.iflytek.cloud.thirdparty.h.n(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "sendTime";
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "mac";
        r4 = com.iflytek.cloud.thirdparty.h.g(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "deviceDetail";
        r4 = com.iflytek.cloud.thirdparty.h.a();	 Catch:{ UnsupportedEncodingException -> 0x00f0 }
        r5 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x00f0 }
        r3.put(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x00f0 }
    L_0x0087:
        r0 = "manufacturer";
        r4 = com.iflytek.cloud.thirdparty.h.b();	 Catch:{ UnsupportedEncodingException -> 0x0104 }
        r5 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x0104 }
        r3.put(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x0104 }
    L_0x0098:
        r0 = "phoneOs";
        r4 = com.iflytek.cloud.thirdparty.h.c();	 Catch:{ UnsupportedEncodingException -> 0x010f }
        r5 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x010f }
        r3.put(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x010f }
    L_0x00a9:
        r0 = "accessPoint";
        r4 = com.iflytek.cloud.thirdparty.h.l(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "deviceId";
        r4 = com.iflytek.cloud.thirdparty.h.a(r7);	 Catch:{ JSONException -> 0x00fb }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        r0 = "channel";
        r4 = com.iflytek.cloud.thirdparty.h.i(r7);	 Catch:{ UnsupportedEncodingException -> 0x011a }
        r5 = "UTF-8";
        r4 = java.net.URLEncoder.encode(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x011a }
        r3.put(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x011a }
    L_0x00ce:
        r0 = "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:posteventlog";
        r0 = a(r7, r0, r3);	 Catch:{ JSONException -> 0x00fb }
        if (r0 == r2) goto L_0x00d9;
    L_0x00d7:
        if (r0 != r6) goto L_0x012f;
    L_0x00d9:
        if (r9 != 0) goto L_0x0125;
    L_0x00db:
        r0 = 3;
        b(r7, r0);	 Catch:{ JSONException -> 0x00fb }
        i(r7, r8);	 Catch:{ JSONException -> 0x00fb }
        r0 = "MobileAgent";
        r1 = "evn log sd";
        android.util.Log.i(r0, r1);	 Catch:{ JSONException -> 0x00fb }
    L_0x00eb:
        r0 = r2;
    L_0x00ec:
        return r0;
    L_0x00ed:
        r0 = r9;
        goto L_0x0009;
    L_0x00f0:
        r0 = move-exception;
        r0 = "deviceDetail";
        r4 = "";
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        goto L_0x0087;
    L_0x00fb:
        r0 = move-exception;
        i(r7, r8);
        r0.printStackTrace();
        r0 = r2;
        goto L_0x00ec;
    L_0x0104:
        r0 = move-exception;
        r0 = "manufacturer";
        r4 = "";
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        goto L_0x0098;
    L_0x010f:
        r0 = move-exception;
        r0 = "phoneOs";
        r4 = "";
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        goto L_0x00a9;
    L_0x011a:
        r0 = move-exception;
        r0 = "channel";
        r4 = "";
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00fb }
        goto L_0x00ce;
    L_0x0125:
        r0 = "MobileAgent";
        r1 = "evnrt log sd";
        android.util.Log.i(r0, r1);	 Catch:{ JSONException -> 0x00fb }
        goto L_0x00eb;
    L_0x012f:
        r3 = 2;
        if (r0 != r3) goto L_0x013d;
    L_0x0132:
        r0 = "MobileAgent";
        r3 = "please check your network";
        android.util.Log.w(r0, r3);	 Catch:{ JSONException -> 0x00fb }
        r0 = r1;
        goto L_0x00ec;
    L_0x013d:
        r0 = r1;
        goto L_0x00ec;
    L_0x013f:
        if (r9 != 0) goto L_0x0149;
    L_0x0141:
        b(r7, r6);
        i(r7, r8);
        r0 = r2;
        goto L_0x00ec;
    L_0x0149:
        r0 = r1;
        goto L_0x00ec;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.b.e(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    protected static void f(Context context) {
        if (f) {
            if (g(context, "updateonlyonwifi").equals("1")) {
                e = true;
            } else {
                e = false;
            }
            String g = g(context, "updatedelay");
            if (!g.equals("0")) {
                h = Integer.parseInt(g) * 1000;
            }
            int parseInt = Integer.parseInt(g(context, "send_policy"));
            g = parseInt;
            if (parseInt == 0) {
                g = 1;
            }
        }
    }

    protected static void f(Context context, String str) {
        if (!str.trim().equals("")) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("occurtime", System.currentTimeMillis());
                jSONObject2.put("errmsg", URLEncoder.encode(str, "UTF-8"));
                jSONArray.put(jSONObject2);
                jSONObject.put(SpeechConstant.IST_SESSION_ID, p);
                jSONObject.put("errjsonary", jSONArray);
                c.a("MobileAgentRun", "errJso" + jSONObject.toString());
                a(context, jSONObject.toString(), 4);
            } catch (JSONException e) {
                c.a("MobileAgentRun", "json exception,lost catch Exception");
            } catch (UnsupportedEncodingException e2) {
                c.a("MobileAgentRun", "unsupport utf-8,lost catch Exception");
            }
        }
    }

    protected static boolean f(Context context, String str, String str2) {
        String h = str2 == null ? h(context, str) : str2;
        if (!h.equals("")) {
            Object obj = h.contains("%23lxapkmd5") ? 1 : null;
            try {
                JSONObject jSONObject = new JSONObject(h);
                h = h.b(context);
                jSONObject.put("pid", 1);
                jSONObject.put("protocolVersion", "3.1.4");
                jSONObject.put("sdkVersion", "3.2.0.2");
                jSONObject.put("cid", h);
                jSONObject.put(WBConstants.SSO_APP_KEY, h.h(context));
                jSONObject.put("packageName", h.j(context));
                jSONObject.put("versionCode", h.m(context));
                jSONObject.put("versionName", h.n(context));
                jSONObject.put("sendTime", System.currentTimeMillis());
                jSONObject.put("mac", h.g(context));
                try {
                    jSONObject.put("deviceDetail", URLEncoder.encode(h.a(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    jSONObject.put("deviceDetail", "");
                }
                try {
                    jSONObject.put("manufacturer", URLEncoder.encode(h.b(), "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    jSONObject.put("manufacturer", "");
                }
                try {
                    jSONObject.put("phoneOs", URLEncoder.encode(h.c(), "UTF-8"));
                } catch (UnsupportedEncodingException e3) {
                    jSONObject.put("phoneOs", "");
                }
                jSONObject.put("accessPoint", h.l(context));
                jSONObject.put("deviceId", h.a(context));
                try {
                    jSONObject.put(g.b, URLEncoder.encode(h.i(context), "UTF-8"));
                } catch (UnsupportedEncodingException e4) {
                    jSONObject.put(g.b, "");
                }
                int a = a(context, "http://da.mmarket.com/mmsdk/mmsdk?func=mmsdk:specposteventlog", jSONObject);
                if (a == 1 || a == 3) {
                    if (str2 == null) {
                        b(context, 3);
                        i(context, str);
                        Log.i("MobileAgent", "evn log sd");
                    } else {
                        Log.i("MobileAgent", "evnrt log sd");
                    }
                    if (obj != null && a == 1) {
                        d(context, "#lxapkmd5");
                    }
                    return true;
                } else if (a != 2) {
                    return false;
                } else {
                    Log.w("MobileAgent", "please check your network");
                    return false;
                }
            } catch (JSONException e5) {
                i(context, str);
                e5.printStackTrace();
                return true;
            }
        } else if (str2 != null) {
            return false;
        } else {
            b(context, 3);
            i(context, str);
            return true;
        }
    }

    private static String g(Context context, String str) {
        return k(context).getString(str, "0");
    }

    static synchronized void g(Context context) {
        synchronized (b.class) {
            c.a("MobileAgentRun", "run into uploadlist :" + context.getClass().getName());
            if (g == 2) {
                try {
                    Thread.sleep((long) h);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean z = true;
            System.currentTimeMillis();
            while (true) {
                String n = n(context);
                if (n.equals("") || !r0) {
                    c.a("MobileAgentRun", " finish uploadlist ");
                    c.a("MobileAgentRun", "run out uploadlist :" + context.getClass().getName());
                } else {
                    if (n.substring(0, 6).equals(i)) {
                        z = a(context, n);
                    } else if (n.substring(0, 6).equals(j)) {
                        z = e(context, n, null);
                    } else if (n.substring(0, 6).equals(l)) {
                        z = c(context, n);
                    } else if (n.substring(0, 6).equals(m)) {
                        z = b(context, n);
                    } else if (n.substring(0, 6).equals(k)) {
                        z = f(context, n, null);
                    }
                    if (z) {
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                    c.a("MobileAgentRun", "finish a task : " + n);
                }
            }
            c.a("MobileAgentRun", " finish uploadlist ");
            c.a("MobileAgentRun", "run out uploadlist :" + context.getClass().getName());
        }
    }

    private static synchronized String h(Context context, String str) {
        String str2;
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        synchronized (b.class) {
            String str3 = "";
            FileInputStream openFileInput;
            try {
                openFileInput = context.openFileInput(str);
                try {
                    byte[] bArr = new byte[10000];
                    str2 = str3;
                    while (true) {
                        try {
                            int read = openFileInput.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            str2 = new StringBuilder(String.valueOf(str2)).append(new String(bArr, 0, read)).toString();
                        } catch (FileNotFoundException e3) {
                            e = e3;
                        } catch (IOException e4) {
                            e2 = e4;
                        }
                    }
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    str2 = str3;
                    try {
                        e.printStackTrace();
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e2222 = e6;
                    str2 = str3;
                    e2222.printStackTrace();
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e22222) {
                            e22222.printStackTrace();
                        }
                    }
                    return str2;
                }
            } catch (FileNotFoundException e52) {
                openFileInput = null;
                e = e52;
                str2 = str3;
                e.printStackTrace();
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return str2;
            } catch (IOException e62) {
                openFileInput = null;
                e22222 = e62;
                str2 = str3;
                e22222.printStackTrace();
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return str2;
            } catch (Throwable th3) {
                th = th3;
                openFileInput = null;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw th;
            }
        }
        return str2;
    }

    private static void h(Context context) {
        c.a("MobileAgentRun", "run into onresumep :" + context.getClass().getName());
        try {
            c.a("page pro", "do resume start: " + context.getClass().getName());
            b(context, j(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.a("MobileAgentRun", "run out onresume :" + context.getClass().getName());
    }

    private static void h(Context context, String str, String str2) {
        c.a("MobileAgentAPI", "run in onEvent [" + str + "," + str2 + "]");
        if (context == null) {
            Log.e("MobileAgent", "Exception occurent in onEvent ,context cann't be null");
            return;
        }
        if (TextUtils.isEmpty(str) || h.a(str, 100)) {
            Log.e("MobileAgent", "Exception occurent in onEvent ,channelID cann't be empty or length more than 100");
        }
        if (h.a(str2, (int) IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR)) {
            Log.e("MobileAgent", "Exception occurent in onEvent ,channelID cann't be empty or length more than 100");
        }
        new d(context, 11, str, str2).start();
    }

    private static String i(Context context, String str, String str2) {
        long j = 0;
        long currentTimeMillis = System.currentTimeMillis();
        if (str.equals("onResume")) {
            o = currentTimeMillis;
        } else if (str.equals("onPause") && n.equals(context.getClass().getName())) {
            long j2 = currentTimeMillis - o;
            if (j2 > 12000000) {
                j = 300000;
            } else if (j2 >= 0) {
                j = j2;
            }
        }
        if (str2 == null) {
            str2 = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(str);
        stringBuffer.append("|");
        stringBuffer.append(context.getClass().getName());
        stringBuffer.append("|");
        stringBuffer.append(currentTimeMillis);
        stringBuffer.append("|");
        stringBuffer.append(j);
        stringBuffer.append("|");
        stringBuffer.append(n);
        stringBuffer.append("\n");
        n = context.getClass().getName();
        return stringBuffer.toString();
    }

    private static void i(Context context) {
        c.a("MobileAgentRun", "run into onpausep :" + context.getClass().getName());
        SharedPreferences l = l(context);
        String string = l.getString("activities", null);
        Editor edit = l.edit();
        edit.putLong("endTime", System.currentTimeMillis());
        edit.putString("activities", i(context, "onPause", string));
        edit.commit();
        c.a("MobileAgentRun", "run out onpausep :" + context.getClass().getName());
    }

    private static void i(Context context, String str) {
        if (str != null) {
            context.deleteFile(str);
            j(context, str);
        }
    }

    private static void j(Context context, String str) {
        synchronized (q) {
            SharedPreferences c = c(context);
            c.edit().putString("uploadList", c.getString("uploadList", "").replace(new StringBuilder(String.valueOf(str)).append("|").toString(), "")).commit();
        }
    }

    private static boolean j(Context context) {
        boolean z = true;
        c.a("MobileAgentRun", "run into sessionpolicy");
        String h = h.h(context);
        SharedPreferences l = l(context);
        String string = l.getString(Parameters.SESSION_ID, null);
        Editor edit;
        if (a(l)) {
            if (string != null) {
                a(context, l, true);
                edit = l.edit();
                Long valueOf = Long.valueOf(l.getLong("readFlowRev", 0));
                Long valueOf2 = Long.valueOf(l.getLong("readFlowSnd", 0));
                edit.clear();
                edit.putLong("readFlowRev", valueOf.longValue()).commit();
                edit.putLong("readFlowSnd", valueOf2.longValue()).commit();
            }
            a(context, h, l);
            a(context, l, false);
            if (a(context, 1)) {
                e(context);
            }
        } else {
            h = l.getString("activities", null);
            edit = l.edit();
            edit.putString("activities", i(context, "onResume", h));
            edit.putLong("lastResumeTime", System.currentTimeMillis());
            edit.commit();
            if (g == 1 || h.getBytes().length > 10000) {
                a(context, l, false);
            }
            z = false;
        }
        c.a("MobileAgentRun", "run out sessionpolicy");
        return z;
    }

    private static SharedPreferences k(Context context) {
        return context.getSharedPreferences(b + "MoblieAgent_config_" + context.getPackageName(), 0);
    }

    private static SharedPreferences l(Context context) {
        return context.getSharedPreferences(b + "MoblieAgent_state_" + context.getPackageName(), 0);
    }

    private static synchronized long m(Context context) {
        long j;
        synchronized (b.class) {
            j = c(context).getLong("uploadpopindex", 0);
        }
        return j;
    }

    private static String n(Context context) {
        String str;
        synchronized (q) {
            str = "";
            String string = c(context).getString("uploadList", "");
            if (!string.equals("")) {
                str = string.substring(0, string.indexOf("|"));
            }
        }
        return str;
    }

    private static boolean o(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }
}
