package com.alibaba.mtl.appmonitor;

import java.util.Map;

class AppMonitor$3 implements Runnable {
    final /* synthetic */ Map a;

    AppMonitor$3(Map map) {
        this.a = map;
    }

    public void run() {
        try {
            AppMonitor.f4a.turnOnRealTimeDebug(this.a);
        } catch (Exception e) {
            AppMonitor.b(e);
        }
    }
}
