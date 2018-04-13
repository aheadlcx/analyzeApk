package com.microquation.linkedme.android.g;

import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;

public final class e {
    public static int a(@NonNull Context context, @NonNull String str) {
        return a(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }

    public static int a(@NonNull Context context, @NonNull String str, int i, int i2, String str2) {
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String a = a.a(str);
        if (a == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        return a.a(context, a, str2) != 0 ? -2 : 0;
    }
}
