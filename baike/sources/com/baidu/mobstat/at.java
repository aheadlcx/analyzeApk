package com.baidu.mobstat;

import android.content.Context;
import android.content.Intent;
import com.baidu.bottom.service.BottomReceiver;
import com.sina.weibo.sdk.statistic.i;

public class at extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Intent b;
    final /* synthetic */ dd c;
    final /* synthetic */ BottomReceiver d;

    public at(BottomReceiver bottomReceiver, Context context, Intent intent, dd ddVar) {
        this.d = bottomReceiver;
        this.a = context;
        this.b = intent;
        this.c = ddVar;
    }

    public void run() {
        try {
            this.d.b(this.a, this.b);
            this.d.a(this.a, this.b);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - BottomReceiver.b < i.MIN_UPLOAD_INTERVAL) {
                bd.a("No need to handle receiver due to time strategy");
                return;
            }
            BottomReceiver.b = currentTimeMillis;
            ao.RECEIVER.a(this.a);
            this.c.b();
            BottomReceiver.a = null;
        } catch (Throwable th) {
        } finally {
            this.c.b();
            BottomReceiver.a = null;
        }
    }
}
