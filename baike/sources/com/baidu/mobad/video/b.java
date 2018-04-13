package com.baidu.mobad.video;

import com.baidu.mobad.video.XAdContext.AdSlotEventListener;
import com.baidu.mobads.interfaces.IXAdConstants4PDK;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

class b implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ AdSlotEventListener b;

    b(AdSlotEventListener adSlotEventListener, IOAdEvent iOAdEvent) {
        this.b = adSlotEventListener;
        this.a = iOAdEvent;
    }

    public void run() {
        if (this.a.getType().equals(com.baidu.mobads.openad.c.b.COMPLETE)) {
            this.b.c.dispatchEvent(new XAdEvent4PDK(IXAdConstants4PDK.EVENT_REQUEST_COMPLETE, this.b.b));
        }
        if (this.a.getType().equals(IXAdEvent.AD_STARTED)) {
            if (this.b.b.getProdBase() != null) {
                this.b.b.getProdBase().setVisibility(0);
            }
            this.b.c.dispatchEvent(new XAdEvent4PDK(IXAdConstants4PDK.EVENT_SLOT_STARTED, this.b.b));
        }
        if (this.a.getType().equals("AdUserClick")) {
            this.b.c.dispatchEvent(new XAdEvent4PDK(IXAdConstants4PDK.EVENT_SLOT_CLICKED, this.b.b));
        }
        if (this.a.getType().equals(IXAdEvent.AD_STOPPED)) {
            if (this.b.b.getProdBase() != null) {
                this.b.b.getProdBase().setVisibility(4);
            }
            this.b.c.dispatchEvent(new XAdEvent4PDK(IXAdConstants4PDK.EVENT_SLOT_ENDED, this.b.b));
        }
        if (this.a.getType().equals(IXAdEvent.AD_ERROR)) {
            if (this.b.b.getProdBase() != null) {
                this.b.b.getProdBase().setVisibility(4);
            }
            this.b.c.dispatchEvent(new XAdEvent4PDK(IXAdConstants4PDK.EVENT_ERROR, this.b.b));
        }
    }
}
