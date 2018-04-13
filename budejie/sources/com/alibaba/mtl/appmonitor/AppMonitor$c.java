package com.alibaba.mtl.appmonitor;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class AppMonitor$c extends Handler {
    private boolean h = false;

    public AppMonitor$c(Looper looper) {
        super(looper);
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            try {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = runnable;
                sendMessage(obtain);
            } catch (Throwable th) {
            }
        }
    }

    public void handleMessage(Message message) {
        try {
            if (this.h) {
                this.h = false;
                synchronized (AppMonitor.a()) {
                    try {
                        AppMonitor.a().wait(5000);
                    } catch (InterruptedException e) {
                        AppMonitor.d();
                    }
                }
            }
            if (message.obj != null && (message.obj instanceof Runnable)) {
                try {
                    ((Runnable) message.obj).run();
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
        super.handleMessage(message);
    }

    public void a(boolean z) {
        this.h = true;
    }
}
