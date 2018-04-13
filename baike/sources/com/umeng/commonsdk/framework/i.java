package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.internal.b;

class i implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ h b;

    i(h hVar, Context context) {
        this.b = hVar;
        this.a = context;
    }

    public void run() {
        try {
            if (this.a != null) {
                UMWorkDispatch.sendEvent(this.a, a.j, b.a(this.a).a(), null);
            }
        } catch (Throwable th) {
        }
    }
}
