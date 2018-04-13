package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;

class cq extends bk {
    static cq a = new cq();

    private cq() {
    }

    public static cq a() {
        return a;
    }

    public SharedPreferences a(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return context.getSharedPreferences("baidu_mtj_sdk_record", 4);
        }
        return context.getSharedPreferences("baidu_mtj_sdk_record", 0);
    }

    protected void a(Context context, long j) {
        b(context, "session_first_visit_time", j);
    }

    protected Long b(Context context) {
        return Long.valueOf(a(context, "session_first_visit_time", 0));
    }

    protected void b(Context context, long j) {
        b(context, "session_last_visit_time", j);
    }

    protected Long c(Context context) {
        return Long.valueOf(a(context, "session_last_visit_time", 0));
    }

    protected void c(Context context, long j) {
        b(context, "session_visit_interval", j);
    }

    protected Long d(Context context) {
        return Long.valueOf(a(context, "session_visit_interval", 0));
    }

    protected void a(Context context, String str) {
        b(context, "session_today_visit_count", str);
    }

    protected String e(Context context) {
        return a(context, "session_today_visit_count", "");
    }

    protected void b(Context context, String str) {
        b(context, "session_recent_visit", str);
    }

    protected String f(Context context) {
        return a(context, "session_recent_visit", "");
    }
}
