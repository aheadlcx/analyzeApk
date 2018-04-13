package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.error.XAdErrorCode;

class i implements Runnable {
    final /* synthetic */ a a;

    i(a aVar) {
        this.a = aVar;
    }

    public void run() {
        try {
            this.a.a();
            this.a.j();
            this.a.i();
            this.a.a(XAdErrorCode.REQUEST_TIMEOUT, "");
        } catch (Throwable e) {
            this.a.r.e(e);
        }
    }
}
