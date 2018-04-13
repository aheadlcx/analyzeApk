package com.xiaomi.metok.geofencing;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;

public class a {
    private Context a;
    private c b;
    private boolean c = false;
    private final ServiceConnection d = new b(this);

    public a(Context context) {
        this.a = context;
        this.c = false;
        a(context);
    }

    public void a(Context context) {
        if (!this.c && context != null) {
            if (this.b == null) {
                Intent intent = new Intent("com.xiaomi.metok.GeoFencingService");
                intent.setPackage("com.xiaomi.metok");
                try {
                    if (context.bindService(intent, this.d, 1)) {
                        Log.d("GeoFencingServiceWrapper", "GeoFencingService started");
                        this.c = true;
                        return;
                    }
                    Log.d("GeoFencingServiceWrapper", "Can't bind GeoFencingService");
                    this.c = false;
                    return;
                } catch (SecurityException e) {
                    Log.e("GeoFencingServiceWrapper", "SecurityException:" + e);
                    return;
                }
            }
            Log.d("GeoFencingServiceWrapper", "GeoFencingService already started");
        }
    }

    public void a(Context context, double d, double d2, float f, long j, String str, String str2, String str3) {
        a(context);
        if (this.b != null) {
            try {
                this.b.a(d, d2, f, j, str, str2, str3);
                Log.d("GeoFencingServiceWrapper", "calling registerFenceListener success");
            } catch (Throwable e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
    }

    public void a(Context context, String str, String str2) {
        a(context);
        if (this.b != null) {
            try {
                this.b.a(str, str2);
                Log.d("GeoFencingServiceWrapper", "calling unregisterFenceListener success");
            } catch (Throwable e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
    }
}
