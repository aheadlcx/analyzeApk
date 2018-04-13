package com.alibaba.mtl.log.e;

import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.Map;

public class h {
    public static String a(Map<String, String> map) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        for (LogField logField : LogField.values()) {
            if (logField == LogField.ARGS) {
                break;
            }
            if (map.containsKey(logField.toString())) {
                str = ((String) map.get(logField.toString())) + "";
                map.remove(logField.toString());
            } else {
                str = null;
            }
            stringBuilder.append(d(str)).append("||");
        }
        Object obj = 1;
        if (map.containsKey(LogField.ARGS.toString())) {
            stringBuilder.append(d(((String) map.get(LogField.ARGS.toString())) + ""));
            map.remove(LogField.ARGS.toString());
            obj = null;
        }
        Object obj2 = obj;
        for (String str2 : map.keySet()) {
            if (map.containsKey(str2)) {
                str = ((String) map.get(str2)) + "";
            } else {
                str = null;
            }
            if (obj2 != null) {
                if ("StackTrace".equals(str2)) {
                    stringBuilder.append("StackTrace=====>").append(str);
                } else {
                    stringBuilder.append(d(str2)).append(LoginConstants.EQUAL).append(str);
                }
                obj = null;
            } else if ("StackTrace".equals(str2)) {
                stringBuilder.append(",").append("StackTrace=====>").append(str);
                obj = obj2;
            } else {
                stringBuilder.append(",").append(d(str2)).append(LoginConstants.EQUAL).append(str);
                obj = obj2;
            }
            obj2 = obj;
        }
        str = stringBuilder.toString();
        if (TextUtils.isEmpty(str) || !str.endsWith("||")) {
            return str;
        }
        return str + "-";
    }

    public static String b(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        a((Map) map);
        return a((Map) map);
    }

    /* renamed from: a */
    public static Map<String, String> m26a(Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        try {
            CharSequence m = b.m();
            if (!(TextUtils.isEmpty(m) || map.containsKey(LogField.USERNICK.toString()))) {
                map.put(LogField.USERNICK.toString(), m);
            }
            m = b.j();
            if (!(TextUtils.isEmpty(m) || map.containsKey(LogField.LL_USERNICK.toString()))) {
                map.put(LogField.LL_USERNICK.toString(), m);
            }
            m = b.n();
            if (!(TextUtils.isEmpty(m) || map.containsKey(LogField.USERID.toString()))) {
                map.put(LogField.USERID.toString(), m);
            }
            m = b.k();
            if (!(TextUtils.isEmpty(m) || map.containsKey(LogField.LL_USERID.toString()))) {
                map.put(LogField.LL_USERID.toString(), m);
            }
            String valueOf = String.valueOf(System.currentTimeMillis());
            if (!map.containsKey(LogField.RECORD_TIMESTAMP.toString())) {
                map.put(LogField.RECORD_TIMESTAMP.toString(), valueOf);
            }
            if (!map.containsKey(LogField.START_SESSION_TIMESTAMP.toString())) {
                map.put(LogField.START_SESSION_TIMESTAMP.toString(), String.valueOf(a.B));
            }
            Map a = d.a(a.getContext());
            if (a != null) {
                for (String valueOf2 : a.keySet()) {
                    String str = (String) a.get(valueOf2);
                    if (!(TextUtils.isEmpty(str) || map.containsKey(valueOf2))) {
                        map.put(valueOf2, str);
                    }
                }
            }
            m = t();
            if (!(TextUtils.isEmpty(m) || map.containsKey(LogField.RESERVES.toString()))) {
                map.put(LogField.RESERVES.toString(), m);
            }
        } catch (Throwable th) {
        }
        return map;
    }

    private static String t() {
        String str = "_ap=1";
        if (l.getWifiAddress(a.getContext()) != null) {
            str = str + String.format("%s=%s", new Object[]{"_mac", r1});
        }
        if (!d.k()) {
            return str;
        }
        Object q = d.q();
        if (TextUtils.isEmpty(q)) {
            return str;
        }
        return str + ",_did=" + q;
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "-";
        }
        return str;
    }

    public static String a(String str, String str2, String str3, String str4, String str5, Map<String, String> map, String str6, String str7) {
        Map hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put(LogField.PAGE.toString(), str);
        }
        hashMap.put(LogField.EVENTID.toString(), str2);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(LogField.ARG1.toString(), str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(LogField.ARG2.toString(), str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            hashMap.put(LogField.ARG3.toString(), str5);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put(LogField.RECORD_TIMESTAMP.toString(), str7);
        }
        if (!TextUtils.isEmpty(str6)) {
            hashMap.put(LogField.RESERVE3.toString(), str6);
        }
        return b(hashMap);
    }
}
