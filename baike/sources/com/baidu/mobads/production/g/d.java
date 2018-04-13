package com.baidu.mobads.production.g;

import com.baidu.mobads.f.a;
import com.baidu.mobads.interfaces.event.IXAdEvent;

class d implements Runnable {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.dispatchEvent(new a(IXAdEvent.AD_STOPPED));
    }
}
