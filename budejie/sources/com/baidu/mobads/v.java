package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.c.b;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

class v implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ u b;

    v(u uVar, IOAdEvent iOAdEvent) {
        this.b = uVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.c.onVideoPrepared();
        }
        if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.c.onVideoStart();
        }
        if (IXAdEvent.AD_CLICK_THRU.equals(this.a.getType())) {
            this.b.a.c.onVideoClickAd();
        }
        if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            this.b.a.c.onVideoFinish();
        }
        if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            String str = (String) this.a.getData().get(b.EVENT_MESSAGE);
            this.b.a.c.onVideoError();
        }
    }
}
