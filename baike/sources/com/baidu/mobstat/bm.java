package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Map;

class bm {
    private static HandlerThread b = new HandlerThread("EventHandleThread");
    private static Handler c;
    private static bm d = new bm();
    HashMap<String, bs> a = new HashMap();

    private bm() {
        b.start();
        b.setPriority(10);
        c = new Handler(b.getLooper());
    }

    public static bm a() {
        return d;
    }

    public void a(Context context, String str, String str2, int i, long j, long j2, ExtraInfo extraInfo, Map<String, String> map) {
        DataCore.instance().putEvent(context, str, str2, i, j, j2, "", "", 0, false, extraInfo, map);
        DataCore.instance().flush(context);
    }

    public void a(Context context, String str, String str2, int i, long j, long j2, String str3, String str4, int i2, boolean z) {
        DataCore.instance().putEvent(context, str, str2, i, j, j2, str3, str4, i2, z, null, null);
        DataCore.instance().flush(context);
    }

    public void a(Context context, String str, String str2, int i, long j, String str3, String str4, int i2, boolean z) {
        c.post(new bn(this, context, str, str2, i, j, str3, str4, i2, z));
    }

    public void a(Context context, String str, String str2, int i, long j, ExtraInfo extraInfo, Map<String, String> map) {
        c.post(new bo(this, context, str, str2, i, j, extraInfo, map));
    }

    public void a(Context context, String str, String str2, long j) {
        c.post(new bp(this, j, str, str2));
    }

    public void a(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        c.post(new bq(this, str, str2, j, context, extraInfo, map));
    }

    public void b(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        c.post(new br(this, j, context, str, str2, extraInfo, map));
    }

    public String a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }
}
