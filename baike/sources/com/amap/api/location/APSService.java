package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.loc.au;
import com.loc.cw;
import com.loc.dc;
import com.loc.f;

public class APSService extends Service {
    APSServiceBase a;

    public IBinder onBind(Intent intent) {
        try {
            return this.a.onBind(intent);
        } catch (Throwable th) {
            cw.a(th, "APSService", "onBind");
            return null;
        }
    }

    public void onCreate() {
        onCreate(this);
    }

    public void onCreate(Context context) {
        try {
            if (dc.d(context)) {
                Context context2 = context;
                this.a = (APSServiceBase) au.a(context2, cw.b(), "com.amap.api.location.APSServiceWrapper", f.class, new Class[]{Context.class}, new Object[]{context});
            } else if (this.a == null) {
                this.a = new f(context);
            }
        } catch (Throwable th) {
        }
        try {
            if (this.a == null) {
                this.a = new f(context);
            }
            this.a.onCreate();
        } catch (Throwable th2) {
            cw.a(th2, "APSService", "onCreate");
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.a.onDestroy();
        } catch (Throwable th) {
            cw.a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.a.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            cw.a(th, "APSService", "onStartCommand");
            return super.onStartCommand(intent, i, i2);
        }
    }
}
