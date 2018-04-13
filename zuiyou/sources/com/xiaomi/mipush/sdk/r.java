package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient.a.a;
import com.xiaomi.xmpush.thrift.f;

class r implements Runnable {
    final /* synthetic */ f a;
    final /* synthetic */ a b;

    r(a aVar, f fVar) {
        this.b = aVar;
        this.a = fVar;
    }

    public void run() {
        this.b.a.add(this.a);
        this.b.a();
    }
}
