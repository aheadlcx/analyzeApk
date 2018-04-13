package com.microquation.linkedme.android.g;

import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;

public class c {
    public static int a(@NonNull Context context, @NonNull String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}
