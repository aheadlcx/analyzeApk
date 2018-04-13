package com.umeng.commonsdk.framework;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.b;
import java.io.File;
import java.util.Comparator;

final class d implements Comparator<File> {
    final /* synthetic */ Context a;

    d(Context context) {
        this.a = context;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        Object b = b.c(name);
        Object b2 = b.c(name2);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(b2)) {
            return 1;
        }
        try {
            long longValue = Long.valueOf(b).longValue() - Long.valueOf(b2).longValue();
            if (longValue > 0) {
                return 1;
            }
            if (longValue == 0) {
                return 0;
            }
            return -1;
        } catch (Throwable e) {
            b.a(this.a, e);
            return 1;
        }
    }
}
