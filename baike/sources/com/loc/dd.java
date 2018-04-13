package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public final class dd {
    public static String a(Context context) {
        Throwable th;
        String str = "00:00:00:00:00:00";
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                String str2 = "smac";
                if (sharedPreferences.contains(str2)) {
                    try {
                        String string = sharedPreferences.getString(str2, null);
                        try {
                            str = new String(o.b(string), "UTF-8");
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            str = string;
                            th = th3;
                            cw.a(th, "SPUtil", "getSmac");
                            sharedPreferences.edit().remove(str2).commit();
                            return str;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        cw.a(th, "SPUtil", "getSmac");
                        sharedPreferences.edit().remove(str2).commit();
                        return str;
                    }
                }
            }
        }
        return str;
    }

    public static String a(Context context, String str, String str2, String str3) {
        try {
            str3 = context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "getPrefsInt");
        }
        return str3;
    }

    public static void a(Context context, String str, String str2, int i) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(str2, i);
            a(edit);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "setPrefsInt");
        }
    }

    public static void a(Context context, String str, String str2, long j) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putLong(str2, j);
            a(edit);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "setPrefsLong");
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putBoolean(str2, z);
            a(edit);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "updatePrefsBoolean");
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                editor.apply();
                return;
            }
            try {
                new ec(editor).execute(new Void[]{null, null, null});
            } catch (Throwable th) {
                cw.a(th, "SPUtil", "commit1");
            }
        }
    }

    public static int b(Context context, String str, String str2, int i) {
        try {
            i = context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "getPrefsInt");
        }
        return i;
    }

    public static long b(Context context, String str, String str2, long j) {
        try {
            j = context.getSharedPreferences(str, 0).getLong(str2, j);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "getPrefsLong");
        }
        return j;
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        try {
            z = context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "getPrefsBoolean");
        }
        return z;
    }
}
