package org.greenrobot.eventbus.util;

import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.util.AsyncExecutor.RunnableEx;

class a implements Runnable {
    final /* synthetic */ RunnableEx a;
    final /* synthetic */ AsyncExecutor b;

    a(AsyncExecutor asyncExecutor, RunnableEx runnableEx) {
        this.b = asyncExecutor;
        this.a = runnableEx;
    }

    public void run() {
        try {
            this.a.run();
        } catch (Throwable e) {
            try {
                Object newInstance = this.b.b.newInstance(new Object[]{e});
                if (newInstance instanceof HasExecutionScope) {
                    ((HasExecutionScope) newInstance).setExecutionScope(this.b.d);
                }
                this.b.c.post(newInstance);
            } catch (Throwable e2) {
                Log.e(EventBus.TAG, "Original exception:", e);
                throw new RuntimeException("Could not create failure event", e2);
            }
        }
    }
}
