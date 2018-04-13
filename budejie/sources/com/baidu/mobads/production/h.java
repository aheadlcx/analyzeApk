package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.download.activate.IXMonitorActivation;

class h implements Runnable {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void run() {
        try {
            if (a.a != null) {
                IXMonitorActivation xMonitorActivation = a.a.getXMonitorActivation(this.a.a, this.a.b.s);
                xMonitorActivation.setIXActivateListener(new a(this.a.b.f));
                xMonitorActivation.startMonitor();
            }
        } catch (Throwable e) {
            this.a.b.s.e(e);
        }
    }
}
