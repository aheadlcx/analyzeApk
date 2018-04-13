package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class ca {
    public static boolean a = true;
    private static final Pattern b = Pattern.compile("[0-3][0-9a-f]{32}");
    private static String c = null;

    public static synchronized String a(Context context) {
        String str;
        synchronized (ca.class) {
            if (c == null) {
                c = b(context);
            }
            str = c;
        }
        return str;
    }

    private static String a(Context context, String str) {
        String str2 = "";
        try {
            str2 = System.getString(context.getContentResolver(), str);
        } catch (Throwable e) {
            cb.b(e);
        }
        return str2;
    }

    private static String a(File file) {
        try {
            if (file.exists() && file.canRead()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[33];
                int read = fileInputStream.read(bArr);
                fileInputStream.close();
                return new String(bArr, 0, read);
            }
        } catch (Throwable th) {
            cb.b(th);
        }
        return null;
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString();
    }

    private static void a(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (a(9)) {
                file.getClass().getMethod("setReadable", new Class[]{Boolean.TYPE, Boolean.TYPE}).invoke(file, new Object[]{Boolean.valueOf(true), Boolean.valueOf(false)});
                return;
            }
            Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
        } catch (Throwable th) {
            cb.b(th);
        }
    }

    private static boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    private static final boolean a(String str) {
        return str == null || "".equals(str.trim()) || !b.matcher(str).matches();
    }

    private static String b(final Context context) {
        String a = a(context, "iflytek.deviceid.key");
        if (!a(a)) {
            return a;
        }
        a = b(context, ".2F6E2C5B63F0F83B");
        if (a(a)) {
            a = b(context, "com.iflytek.id", "pref.deviceid.key", null);
            if (a(a)) {
                a = c(context);
                new Thread(new Runnable() {
                    public void run() {
                        ca.c(context, "iflytek.deviceid.key", a);
                        ca.d(context, ".2F6E2C5B63F0F83B", a);
                        ca.c(context, "com.iflytek.id", "pref.deviceid.key", a);
                    }
                }).start();
                return a;
            }
            new Thread(new Runnable() {
                public void run() {
                    ca.c(context, "iflytek.deviceid.key", a);
                    ca.d(context, ".2F6E2C5B63F0F83B", a);
                }
            }).start();
            return a;
        }
        new Thread(new Runnable() {
            public void run() {
                ca.c(context, "iflytek.deviceid.key", a);
            }
        }).start();
        return a;
    }

    private static String b(Context context, String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                cb.a("create file:" + str);
                return a(new File(Environment.getExternalStorageDirectory() + "/msc", str));
            } catch (Throwable th) {
                cb.b(th);
            }
        }
        return null;
    }

    private static String b(Context context, String str, String str2, String str3) {
        try {
            str3 = context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            cb.b(th);
        }
        return str3;
    }

    private static String b(String str) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }

    private static String c(Context context) {
        return "2" + b(d(context));
    }

    private static void c(Context context, String str, String str2) {
        try {
            System.putString(context.getContentResolver(), str, str2);
        } catch (Throwable e) {
            cb.b(e);
        }
    }

    private static void c(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putString(str2, str3);
                edit.commit();
            }
        } catch (Throwable th) {
            cb.b(th);
        }
    }

    private static String d(Context context) {
        ce a = bp.a(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.e("os.imei")).append('-').append(a.e("os.android_id"));
        if (a) {
            stringBuilder.append('-').append(bp.d(context));
        }
        return stringBuilder.toString();
    }

    private static void d(Context context, String str, String str2) {
        a(new File(Environment.getExternalStorageDirectory() + "/msc", str), str2);
    }
}
