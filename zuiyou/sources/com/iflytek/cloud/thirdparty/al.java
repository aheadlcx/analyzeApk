package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class al {
    private static Map<String, Long> a = new HashMap();
    private static Object b = new Object();
    private static Object c = new Object();
    private static String d = "";

    public static String a(Context context, long j, String str) {
        String a;
        synchronized (c) {
            a = ca.a(context);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(j).append(str).append(a);
            d = j + bu.a(stringBuilder.toString()).substring(8, 24);
            a = d;
        }
        return a;
    }

    public static String a(String str) {
        String format;
        synchronized (b) {
            a.put(str, Long.valueOf(!a.containsKey(str) ? 1 : (((Long) a.get(str)).longValue() + 1) & 65535));
            format = String.format(Locale.ENGLISH, "%s-%d", new Object[]{str, Long.valueOf(r0)});
        }
        return format;
    }

    public static String a(String str, String str2, String str3) {
        try {
            String str4;
            String substring = String.format("%x", new Object[]{Long.valueOf(System.currentTimeMillis() - 1285862400000L)}).substring(0, 8);
            if (TextUtils.isEmpty(str3)) {
                str4 = "00000000";
            } else {
                str4 = String.format("%08x", new Object[]{Long.valueOf(Long.parseLong(str3.substring(1)))});
            }
            String substring2 = str4.substring(2, 6);
            str4 = str4.substring(6, 8);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2);
            stringBuffer.append(str);
            stringBuffer.append("@");
            stringBuffer.append("ch");
            stringBuffer.append(substring2);
            stringBuffer.append(substring);
            stringBuffer.append("460d");
            stringBuffer.append(str4);
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a() {
        synchronized (c) {
            d = "";
        }
    }

    public static String b() {
        String str;
        synchronized (c) {
            str = d;
        }
        return str;
    }
}
