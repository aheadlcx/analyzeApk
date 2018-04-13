package com.umeng.commonsdk.framework;

import android.content.Context;

class j implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ h b;

    j(h hVar, Context context) {
        this.b = hVar;
        this.a = context;
    }

    public void run() {
        try {
            Class cls = Class.forName("com.umeng.commonsdk.internal.oplus.UMUdpSenderAgent");
            if (cls != null) {
                cls.getMethod("DoSendUdpGroupMsg", new Class[]{Context.class}).invoke(cls, new Object[]{this.a});
            }
        } catch (ClassNotFoundException e) {
        } catch (Throwable th) {
        }
    }
}
