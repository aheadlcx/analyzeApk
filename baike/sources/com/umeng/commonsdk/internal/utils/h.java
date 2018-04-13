package com.umeng.commonsdk.internal.utils;

import android.os.Build;
import com.umeng.commonsdk.internal.utils.e.a;
import java.io.File;

public class h {
    public static boolean a() {
        if (b() || c() || d() || e()) {
            return true;
        }
        return false;
    }

    private static boolean b() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            return false;
        }
        return true;
    }

    private static boolean c() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (new File("/system/app/Kinguser.apk").exists()) {
                return true;
            }
        } catch (Exception e2) {
        }
        return false;
    }

    private static boolean d() {
        if (new e().a(a.check_su_binary) != null) {
            return true;
        }
        return false;
    }

    private static boolean e() {
        for (String str : new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
