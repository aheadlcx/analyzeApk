package com.baidu.mobads.production.g;

import com.baidu.mobads.f.a;
import com.baidu.mobads.interfaces.event.IXAdEvent;

class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.dispatchEvent(new a(IXAdEvent.AD_LOADED));
    }
}
