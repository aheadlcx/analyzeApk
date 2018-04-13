package com.loc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.loc.i.a;

final class ds implements ServiceConnection {
    final /* synthetic */ bv a;

    ds(bv bvVar) {
        this.a = bvVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.a = true;
        this.a.f = a.a(iBinder);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.a = false;
        this.a.f = null;
    }
}
