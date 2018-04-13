package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class s implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ r b;

    s(r rVar, IOAdEvent iOAdEvent) {
        this.b = rVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().i(new Object[]{this.a});
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            SplashAd.a(this.b.a).onAdPresent();
        } else if ("AdUserClick".equals(this.a.getType())) {
            SplashAd.a(this.b.a).onAdClick();
        } else if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            SplashAd.b(this.b.a).removeAllListeners();
            SplashAd.a(this.b.a).onAdDismissed();
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            SplashAd.b(this.b.a).removeAllListeners();
            SplashAd.a(this.b.a).onAdFailed(XAdSDKFoundationFacade.getInstance().getErrorCode().getMessage(this.a.getData()));
        }
    }
}
