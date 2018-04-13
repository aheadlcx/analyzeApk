package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.xiaomi.channel.commonutils.misc.a;

public class ao {
    private static ao a;
    private Context b;
    private int c = 0;

    private ao(Context context) {
        this.b = context.getApplicationContext();
    }

    public static ao a(Context context) {
        if (a == null) {
            a = new ao(context);
        }
        return a;
    }

    public boolean a() {
        return a.a.contains("xmsf") || a.a.contains("xiaomi") || a.a.contains("miui");
    }

    @SuppressLint({"NewApi"})
    public int b() {
        if (this.c != 0) {
            return this.c;
        }
        if (VERSION.SDK_INT >= 17) {
            this.c = Global.getInt(this.b.getContentResolver(), "device_provisioned", 0);
            return this.c;
        }
        this.c = Secure.getInt(this.b.getContentResolver(), "device_provisioned", 0);
        return this.c;
    }

    @SuppressLint({"NewApi"})
    public Uri c() {
        return VERSION.SDK_INT >= 17 ? Global.getUriFor("device_provisioned") : Secure.getUriFor("device_provisioned");
    }
}
