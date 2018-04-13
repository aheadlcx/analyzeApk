package com.meituan.android.walle;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;

public final class f {
    @Nullable
    public static String a(@NonNull Context context) {
        return a(context, null);
    }

    @Nullable
    public static String a(@NonNull Context context, @NonNull String str) {
        b b = b(context);
        return b == null ? str : b.a();
    }

    @Nullable
    public static b b(@NonNull Context context) {
        Object c = c(context);
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        return c.a(new File(c));
    }

    @Nullable
    private static String c(@NonNull Context context) {
        String str = null;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                str = applicationInfo.sourceDir;
            }
        } catch (Throwable th) {
        }
        return str;
    }
}
