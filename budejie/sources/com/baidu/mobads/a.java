package com.baidu.mobads;

import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class a implements IOAdEventListener {
    final /* synthetic */ AdView a;

    a(AdView adView) {
        this.a = adView;
    }

    public void run(IOAdEvent iOAdEvent) {
        if (AdView.a(this.a) != null) {
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new b(this, iOAdEvent));
        }
    }
}
