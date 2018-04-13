package com.xiaomi.metoknlp.geofencing;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class a {
    private Context a;
    private c b;
    private boolean c = false;
    private int d = 0;
    private List<b> e = new ArrayList();
    private List<b> f = new ArrayList();
    private Handler g;
    private final ServiceConnection h = new b(this);

    private class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.d = this.a.d + 1;
                    this.a.a(this.a.a);
                    Log.w("GeoFencingServiceWrapper", "Try bindService count=" + this.a.d + ",mBinded=" + this.a.c);
                    if (!this.a.c && this.a.g != null && this.a.d < 10) {
                        this.a.g.sendEmptyMessageDelayed(1, 10000);
                        return;
                    }
                    return;
                case 2:
                    this.a.a();
                    return;
                case 3:
                    this.a.b();
                    return;
                default:
                    Log.w("GeoFencingServiceWrapper", "unknown message type ");
                    return;
            }
        }
    }

    private class b {
        public double a;
        public double b;
        public float c;
        public long d;
        public String e;
        public String f;
        public String g;
        final /* synthetic */ a h;

        public b(a aVar, double d, double d2, float f, long j, String str, String str2, String str3) {
            this.h = aVar;
            this.a = d;
            this.b = d2;
            this.c = f;
            this.d = j;
            this.e = str;
            this.f = str2;
            this.g = str3;
        }
    }

    public a(Context context) {
        this.a = context;
        this.c = false;
        a(context);
        HandlerThread handlerThread = new HandlerThread("GeoFencingServiceWrapper");
        handlerThread.start();
        this.g = new a(this, handlerThread.getLooper());
        if (!this.c) {
            this.g.sendEmptyMessageDelayed(1, 10000);
        }
    }

    private void a() {
        Log.d("GeoFencingServiceWrapper", "try registerPendingFence size=" + (this.e == null ? 0 : this.e.size()));
        for (b bVar : this.e) {
            if (!(bVar == null || this.b == null)) {
                try {
                    this.b.a(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e, bVar.f, bVar.g);
                } catch (RemoteException e) {
                    Log.w("GeoFencingServiceWrapper", "registerPendingFence:" + e);
                }
            }
        }
        if (this.e != null) {
            this.e.clear();
        }
    }

    private void b() {
        Log.d("GeoFencingServiceWrapper", "try unregisterPendingFence size=" + (this.f == null ? 0 : this.f.size()));
        for (b bVar : this.f) {
            if (!(bVar == null || this.b == null)) {
                try {
                    this.b.a(bVar.e, bVar.f);
                } catch (RemoteException e) {
                    Log.w("GeoFencingServiceWrapper", "unregisterPendingFence:" + e);
                }
            }
        }
        if (this.f != null) {
            this.f.clear();
        }
    }

    public void a(Context context) {
        if (!this.c && context != null) {
            if (this.b == null) {
                Intent intent = new Intent("com.xiaomi.metoknlp.GeoFencingService");
                intent.setPackage("com.xiaomi.metoknlp");
                try {
                    if (context.bindService(intent, this.h, 1)) {
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
                return;
            } catch (Throwable e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
        Log.d("GeoFencingServiceWrapper", "registerFenceListener service not ready, add to pending list.");
        this.e.add(new b(this, d, d2, f, j, str, str2, str3));
    }

    public void a(Context context, String str, String str2) {
        a(context);
        if (this.b != null) {
            try {
                this.b.a(str, str2);
                Log.d("GeoFencingServiceWrapper", "calling unregisterFenceListener success");
                return;
            } catch (Throwable e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
        Log.d("GeoFencingServiceWrapper", "unregisterFenceListener service not ready, add to pending list.");
        this.f.add(new b(this, 0.0d, 0.0d, 0.0f, -1, str, str2, ""));
    }
}
