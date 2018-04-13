package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient.a.a;

class s implements Runnable {
    final /* synthetic */ a a;

    s(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (this.a.a.size() != 0) {
            this.a.b();
        } else if (this.a.d != null) {
            this.a.d.cancel(false);
            this.a.d = null;
        }
    }
}
