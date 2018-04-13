package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.sina.weibo.sdk.utils.LogUtil;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class c {
    public static final String KEY_AID = "aid";
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_END_TIME = "endtime";
    public static final String KEY_HASH = "key_hash";
    public static final String KEY_PACKAGE_NAME = "packagename";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_START_TIME = "starttime";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERSION = "version";
    public static final long MAX_INTERVAL = 86400000;

    public static String getAppKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("WEIBO_APPKEY");
                if (obj != null) {
                    LogUtil.i(WBAgent.TAG, "APPKEY: " + String.valueOf(obj));
                    return String.valueOf(obj);
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml." + e);
        }
        return null;
    }

    public static String getChannel(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("WEIBO_CHANNEL");
                if (string != null) {
                    LogUtil.i(WBAgent.TAG, "CHANNEL: " + string.trim());
                    return string.trim();
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml." + e);
        }
        return null;
    }

    public static String getVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            LogUtil.i(WBAgent.TAG, "versionName: " + packageInfo.versionName);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            LogUtil.e(WBAgent.TAG, "Could not read versionName from AndroidManifest.xml." + e);
            return null;
        }
    }

    public static String getPageLogs(CopyOnWriteArrayList<h> copyOnWriteArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append(a((h) it.next()).toString()).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject a(com.sina.weibo.sdk.statistic.h r6) {
        /*
        r1 = new org.json.JSONObject;
        r1.<init>();
        r0 = com.sina.weibo.sdk.statistic.d.a;	 Catch:{ Exception -> 0x0028 }
        r2 = r6.getType();	 Catch:{ Exception -> 0x0028 }
        r2 = r2.ordinal();	 Catch:{ Exception -> 0x0028 }
        r0 = r0[r2];	 Catch:{ Exception -> 0x0028 }
        switch(r0) {
            case 1: goto L_0x0015;
            case 2: goto L_0x0042;
            case 3: goto L_0x0061;
            case 4: goto L_0x0089;
            case 5: goto L_0x00ab;
            case 6: goto L_0x00d4;
            default: goto L_0x0014;
        };	 Catch:{ Exception -> 0x0028 }
    L_0x0014:
        return r1;
    L_0x0015:
        r0 = "type";
        r2 = 0;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
    L_0x0028:
        r0 = move-exception;
        r2 = "WBAgent";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get page log error.";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.sina.weibo.sdk.utils.LogUtil.e(r2, r0);
        goto L_0x0014;
    L_0x0042:
        r0 = "type";
        r2 = 1;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getEndTime();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
    L_0x0061:
        r0 = "type";
        r2 = 2;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
    L_0x0089:
        r0 = "type";
        r2 = 3;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r6 = (com.sina.weibo.sdk.statistic.b) r6;	 Catch:{ Exception -> 0x0028 }
        a(r1, r6);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
    L_0x00ab:
        r0 = "type";
        r2 = 4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "page_id";
        r2 = r6.getPage_id();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "duration";
        r2 = r6.getDuration();	 Catch:{ Exception -> 0x0028 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
    L_0x00d4:
        r6 = (com.sina.weibo.sdk.statistic.a) r6;	 Catch:{ Exception -> 0x0028 }
        r0 = "type";
        r2 = 0;
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "page_id";
        r2 = r6.getmImei();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "time";
        r2 = r6.getStartTime();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        r0 = "aid";
        r2 = r6.getmAid();	 Catch:{ Exception -> 0x0028 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x0028 }
        a(r1, r6);	 Catch:{ Exception -> 0x0028 }
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.c.a(com.sina.weibo.sdk.statistic.h):org.json.JSONObject");
    }

    private static JSONObject a(JSONObject jSONObject, b bVar) {
        try {
            jSONObject.put("event_id", bVar.getEvent_id());
            if (bVar.getExtend() != null) {
                Map extend = bVar.getExtend();
                StringBuilder stringBuilder = new StringBuilder();
                int i = 0;
                for (String str : extend.keySet()) {
                    if (i >= 10) {
                        break;
                    }
                    int i2;
                    if (TextUtils.isEmpty((CharSequence) extend.get(str))) {
                        i2 = i;
                    } else {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append("|");
                        }
                        stringBuilder.append(str).append(Config.TRACE_TODAY_VISIT_SPLIT).append((String) extend.get(str));
                        i2 = i + 1;
                    }
                    i = i2;
                }
                jSONObject.put("extend", stringBuilder.toString());
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, "add event log error." + e);
        }
        return jSONObject;
    }

    public static List<JSONArray> getValidUploadLogs(String str) {
        Object a = a(str);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        List<JSONArray> arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONArray jSONArray2 = new JSONObject(a).getJSONArray("applogs");
            int i = 0;
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                if (a(currentTimeMillis, jSONObject.getLong("time") * 1000)) {
                    if (i < 500) {
                        jSONArray.put(jSONObject);
                        i++;
                    } else {
                        arrayList.add(jSONArray);
                        jSONArray = new JSONArray();
                        i = 0;
                    }
                }
            }
            if (jSONArray.length() > 0) {
                arrayList.add(jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static String a(String str) {
        Object appLogs = e.getAppLogs(e.getAppLogPath(e.ANALYTICS_FILE_NAME));
        if (TextUtils.isEmpty(appLogs) && TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{applogs:[");
        if (!TextUtils.isEmpty(appLogs)) {
            stringBuilder.append(appLogs);
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private static boolean a(long j, long j2) {
        if (j - j2 < 86400000) {
            return true;
        }
        return false;
    }
}
