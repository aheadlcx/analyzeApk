package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.k;

public class c {
    private static String[] a = new String[2];

    public static void a(Context context, String str, String str2) {
        a[0] = str;
        a[1] = str2;
        if (context != null) {
            k.a(context).a(str, str2);
        }
    }

    public static String[] a(Context context) {
        if (!TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
            return a;
        }
        if (context != null) {
            String[] a = k.a(context).a();
            if (a != null) {
                a[0] = a[0];
                a[1] = a[1];
                return a;
            }
        }
        return null;
    }

    public static void b(Context context) {
        a[0] = null;
        a[1] = null;
        if (context != null) {
            k.a(context).b();
        }
    }
}
