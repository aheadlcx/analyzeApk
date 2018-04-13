package com.alibaba.mtl.appmonitor;

import android.os.RemoteException;

class AppMonitor$6 implements Runnable {
    AppMonitor$6() {
    }

    public void run() {
        try {
            AppMonitor.f4a.init();
        } catch (RemoteException e) {
            AppMonitor.d();
            AppMonitor.f4a.init();
        } catch (Throwable th) {
        }
    }
}
