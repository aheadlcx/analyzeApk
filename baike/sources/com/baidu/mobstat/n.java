package com.baidu.mobstat;

import android.content.Context;

public class n {
    public static void a(Context context) {
        m.a.a(context);
        az.a(context).a(u.AP_LIST, System.currentTimeMillis());
    }

    public static void a(Context context, String str, String str2) {
        q.a.a(context, str, str2);
        az.a(context).a(u.APP_CHANGE, System.currentTimeMillis());
    }

    public static void a(Context context, boolean z) {
        r.a.a(context, z);
        az.a(context).a(z ? u.APP_SYS_LIST : u.APP_USER_LIST, System.currentTimeMillis());
    }

    public static void b(Context context, boolean z) {
        s.a.a(context, z);
        az.a(context).a(z ? u.APP_TRACE_CURRENT : u.APP_TRACE_HIS, System.currentTimeMillis());
    }

    public static void b(Context context) {
        o.a.a(context);
        az.a(context).a(u.APP_APK, System.currentTimeMillis());
    }
}
