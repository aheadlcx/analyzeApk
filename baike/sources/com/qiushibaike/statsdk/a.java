package com.qiushibaike.statsdk;

import android.content.Context;
import java.lang.ref.WeakReference;

class a implements Runnable {
    WeakReference<Context> a = new WeakReference(this.b);
    final /* synthetic */ Context b;
    final /* synthetic */ LogServerReporter c;

    a(LogServerReporter logServerReporter, Context context) {
        this.c = logServerReporter;
        this.b = context;
    }

    public void run() {
        Context context = (Context) this.a.get();
        if (context != null) {
            CacheLoader.getInstance().checkLoadFinished(context);
            if (this.c.a(context, this.c.isWifiOnly())) {
                DataObjConstructor.getInstance().flush(context);
            }
        }
    }
}
