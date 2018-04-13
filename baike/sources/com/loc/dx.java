package com.loc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

final class dx implements ServiceConnection {
    final /* synthetic */ d a;

    dx(d dVar) {
        this.a = dVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.j = new Messenger(iBinder);
            this.a.y = true;
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "onServiceConnected");
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.j = null;
        this.a.y = false;
    }
}
