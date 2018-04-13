package com.xiaomi.metoknlp.a;

import com.xiaomi.metoknlp.a;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class e {
    private static String a;
    private static String b = null;
    private static String c = null;

    public static String a() {
        if (a != null) {
            return a;
        }
        String b = b(d.b());
        if (b == null) {
            return b(c.a("ro.ril.miui.imei", ""));
        }
        a = b;
        return a;
    }

    public static String a(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a(int i) {
        return i == 1;
    }

    public static String b() {
        if (b != null && !b.isEmpty()) {
            return b;
        }
        b = c.a("ro.product.model", "");
        b = b.replaceAll(" ", "");
        return b;
    }

    private static String b(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.startsWith(",") || str.endsWith(",")) {
            str = str.replace(",", "");
        }
        if (!str.startsWith("0")) {
            return str;
        }
        try {
            return Long.parseLong(str) == 0 ? null : str;
        } catch (Exception e) {
            return null;
        }
    }

    public static String c() {
        if (c != null && !c.isEmpty()) {
            return c;
        }
        c = c.a("ro.build.version.incremental", "");
        return c;
    }

    public static String d() {
        return !a.a() ? a.b() : a.c() ? "cts" : !c.a("ro.product.locale.region", "CN").equals("CN") ? "global" : a.d() ? "alpha" : a.e() ? "dev" : a.f() ? "stable" : "alpha";
    }

    public static int e() {
        int i = -1;
        String a = d.a();
        if (a != null) {
            int length = a.length();
            if (!a.isEmpty() && length > 1) {
                try {
                    i = Integer.parseInt(a.substring(0, 3));
                } catch (Exception e) {
                }
            }
        }
        return i;
    }

    public static int f() {
        int i = -1;
        String a = d.a();
        if (a != null) {
            int length = a.length();
            if (!a.isEmpty() && length > 1) {
                try {
                    i = Integer.parseInt(a.substring(3));
                } catch (Exception e) {
                }
            }
        }
        return i;
    }

    public static String g() {
        String str = "";
        try {
            return a.a().getPackageManager().getPackageInfo(a.a().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String h() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
