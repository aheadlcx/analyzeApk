package com.alibaba.mtl.appmonitor;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alibaba.mtl.appmonitor.IMonitor.Stub;
import com.alibaba.mtl.log.e.i;

class AppMonitor$5 implements ServiceConnection {
    AppMonitor$5() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (AppMonitor$b.Service == AppMonitor.a()) {
            AppMonitor.f4a = Stub.asInterface(iBinder);
            if (AppMonitor.d() && AppMonitor.a() != null) {
                AppMonitor.a().postAtFrontOfQueue(new Runnable(this) {
                    final /* synthetic */ AppMonitor$5 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        AppMonitor.c();
                    }
                });
            }
        }
        synchronized (AppMonitor.a()) {
            AppMonitor.a().notifyAll();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        i.a(AppMonitor.TAG, "[onServiceDisconnected]");
        synchronized (AppMonitor.a()) {
            AppMonitor.a().notifyAll();
        }
        AppMonitor.a(true);
    }
}
