package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import com.tencent.smtt.utils.TbsLog;

final class f extends Handler {
    final /* synthetic */ PreInitCallback a;
    final /* synthetic */ Context b;
    final /* synthetic */ ai c;

    f(Looper looper, PreInitCallback preInitCallback, Context context, ai aiVar) {
        this.a = preInitCallback;
        this.b = context;
        this.c = aiVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                bj d = bi.b().d();
                if (d != null) {
                    d.a(this.b);
                }
                if (this.a != null) {
                    this.a.onViewInitFinished(true);
                    return;
                }
                return;
            case 2:
                if (this.a != null) {
                    this.a.onViewInitFinished(false);
                }
                TbsLog.writeLogToDisk();
                return;
            case 3:
                this.c.a("init_all", (byte) 2);
                this.c.a(7, "tbs://preinit");
                if (this.a != null) {
                    this.a.onCoreInitFinished();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
