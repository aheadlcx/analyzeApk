package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.b;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class l {
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e = new Object();

    public static String a(Context context) {
        String str = null;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
            if (sharedPreferences != null) {
                JSONObject jSONObject = new JSONObject();
                synchronized (e) {
                    jSONObject.put(b, sharedPreferences.getString(b, ""));
                    jSONObject.put(c, sharedPreferences.getString(c, ""));
                    jSONObject.put(d, sharedPreferences.getString(d, ""));
                }
                str = jSONObject.toString();
            }
        } catch (Throwable e) {
            b.a(context, e);
        }
        return str;
    }

    public static void b(Context context) {
        if (!c(context)) {
            new q(new String[]{"unknown", "unknown", "unknown"}, context).start();
        }
    }

    private static void b(Context context, String[] strArr) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
            if (sharedPreferences != null) {
                synchronized (e) {
                    sharedPreferences.edit().putString(b, strArr[0]).putString(c, strArr[1]).putString(d, strArr[2]).commit();
                }
            }
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a, 0);
        if (sharedPreferences == null || TextUtils.isEmpty(sharedPreferences.getString(b, ""))) {
            return false;
        }
        return true;
    }

    public static int a(String str, String str2) throws IOException {
        int i;
        Process exec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String str3 = "";
        do {
            str3 = bufferedReader.readLine();
            if (str3 == null) {
                i = -1;
                break;
            }
        } while (!str3.contains(str2));
        i = 1;
        try {
            if (exec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException e) {
            return -1;
        }
    }

    public static String a() {
        String str = "unknown";
        int i = -1;
        try {
            i = a("ls /sys/class/thermal", "thermal_zone");
        } catch (IOException e) {
        }
        if (i > 0) {
            return "thermal_zone";
        }
        if (i < 0) {
            return "noper";
        }
        return str;
    }

    public static String b() {
        String str = "unknown";
        int i = -1;
        try {
            i = a("ls /", "goldfish");
        } catch (IOException e) {
        }
        if (i > 0) {
            return "goldfish";
        }
        if (i < 0) {
            return "noper";
        }
        return str;
    }

    public static String c() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        String str = "unknown";
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/diskstats"));
            try {
                String str2 = "";
                CharSequence charSequence = "mmcblk";
                CharSequence charSequence2 = "sda";
                CharSequence charSequence3 = "mtd";
                if (bufferedReader != null) {
                    String readLine;
                    do {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.contains(charSequence)) {
                            str = "mmcblk";
                            break;
                        } else if (readLine.contains(charSequence2)) {
                            str = "sda";
                            break;
                        }
                    } while (!readLine.contains(charSequence3));
                    str = "mtd";
                }
            } catch (Exception e) {
                bufferedReader2 = bufferedReader;
            }
        } catch (Exception e2) {
            bufferedReader2 = null;
            BufferedReader bufferedReader3 = bufferedReader2;
            str = "noper";
            bufferedReader = bufferedReader3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable th) {
                }
            }
            return str;
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return str;
    }
}
