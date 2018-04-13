package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.ao;

class w extends ContentObserver {
    final /* synthetic */ u a;

    w(u uVar, Handler handler) {
        this.a = uVar;
        super(handler);
    }

    public void onChange(boolean z) {
        this.a.h = Integer.valueOf(ao.a(this.a.c).b());
        if (this.a.h.intValue() != 0) {
            this.a.c.getContentResolver().unregisterContentObserver(this);
            if (d.d(this.a.c)) {
                this.a.d();
            }
        }
    }
}
