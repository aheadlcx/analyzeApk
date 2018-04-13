package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import org.json.JSONObject;

class b implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ a b;

    b(a aVar, IOAdEvent iOAdEvent) {
        this.b = aVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            AdView.a(this.b.a).onAdReady(this.b.a);
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            AdView.a(this.b.a).onAdSwitch();
            AdView.a(this.b.a).onAdShow(new JSONObject());
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            AdView.a(this.b.a).onAdFailed(XAdSDKFoundationFacade.getInstance().getErrorCode().getMessage(this.a.getData()));
        } else if ("AdUserClick".equals(this.a.getType())) {
            AdView.a(this.b.a).onAdClick(new JSONObject());
        } else if (IXAdEvent.AD_USER_CLOSE.equals(this.a.getType())) {
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a(this.b.a);
            AdView.a(this.b.a).onAdClose(new JSONObject());
        }
    }
}
