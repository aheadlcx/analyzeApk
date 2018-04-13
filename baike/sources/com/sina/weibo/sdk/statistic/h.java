package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class h {
    private static String f = "session";
    private static long g = 1000;
    protected g a;
    protected String b;
    protected long c;
    private long d;
    private long e;

    public h(Context context) {
        this.c = a(context, c.KEY_START_TIME);
        this.d = a(context, c.KEY_END_TIME);
        this.e = this.d - this.c;
    }

    public h(String str) {
        this.b = str;
        this.c = System.currentTimeMillis();
    }

    public h(Context context, long j) {
        this.c = j;
        this.d = g;
        updateSession(context, null, Long.valueOf(this.c), Long.valueOf(this.d));
    }

    public h(String str, long j) {
        this.b = str;
        this.c = j;
    }

    public g getType() {
        return this.a;
    }

    public void setType(g gVar) {
        this.a = gVar;
    }

    public String getPage_id() {
        return this.b;
    }

    public long getStartTime() {
        return this.c;
    }

    public long getEndTime() {
        return this.d;
    }

    public void setDuration(long j) {
        this.e = j;
    }

    public long getDuration() {
        return this.e;
    }

    public void setmStart_time(long j) {
        this.c = j;
    }

    public static boolean isNewSession(Context context, long j) {
        long a = a(context, c.KEY_END_TIME);
        if (a > g) {
            if (j - a > i.kContinueSessionMillis) {
                return true;
            }
            return false;
        } else if (a == g) {
            return false;
        } else {
            return true;
        }
    }

    private static long a(Context context, String str) {
        return context.getSharedPreferences(f, 0).getLong(str, 0);
    }

    public static void updateSession(Context context, String str, Long l, Long l2) {
        Editor edit = context.getSharedPreferences(f, 0).edit();
        if (l.longValue() != 0) {
            edit.putLong(c.KEY_START_TIME, l.longValue());
        }
        edit.putLong(c.KEY_END_TIME, l2.longValue());
        edit.commit();
    }
}
