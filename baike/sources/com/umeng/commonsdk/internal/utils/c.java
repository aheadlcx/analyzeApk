package com.umeng.commonsdk.internal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.umeng.commonsdk.proguard.b;

public class c {
    private static boolean a = false;
    private static Context b = null;
    private BroadcastReceiver c;

    private static class a {
        private static final c a = new c();
    }

    private c() {
        this.c = new n(this);
    }

    public static c a(Context context) {
        if (b == null && context != null) {
            b = context.getApplicationContext();
        }
        return a.a;
    }

    public synchronized boolean a() {
        return a;
    }

    public synchronized void b() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            b.registerReceiver(this.c, intentFilter);
            a = true;
        } catch (Throwable th) {
            b.a(b, th);
        }
    }

    public synchronized void c() {
        try {
            b.unregisterReceiver(this.c);
            a = false;
        } catch (Throwable th) {
            b.a(b, th);
        }
    }
}
