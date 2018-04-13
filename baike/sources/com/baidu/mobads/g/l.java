package com.baidu.mobads.g;

import android.os.Build;
import android.os.Build.VERSION;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.d.a;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.HashMap;

class l implements Runnable {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public void run() {
        try {
            IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
            double d = this.a.a ? g.b.a : 0.0d;
            IOAdEventListener mVar;
            HashMap hashMap;
            c cVar;
            if (this.a.a) {
                mVar = new m(this, d);
                hashMap = new HashMap();
                hashMap.put("v", "" + d);
                hashMap.put("os", "android");
                hashMap.put(IXAdRequestInfo.PHONE_TYPE, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(Build.MODEL));
                hashMap.put(IXAdRequestInfo.BDR, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(VERSION.SDK));
                cVar = new c(uRIUitls.addParameters(g.i, hashMap), "");
                cVar.e = 1;
                this.a.b.j = new a();
                this.a.b.j.addEventListener("URLLoader.Load.Complete", mVar);
                this.a.b.j.addEventListener("URLLoader.Load.Error", mVar);
                this.a.b.j.a(cVar);
            } else {
                mVar = new m(this, d);
                hashMap = new HashMap();
                hashMap.put("v", "" + d);
                hashMap.put("os", "android");
                hashMap.put(IXAdRequestInfo.PHONE_TYPE, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(Build.MODEL));
                hashMap.put(IXAdRequestInfo.BDR, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(VERSION.SDK));
                cVar = new c(uRIUitls.addParameters(g.i, hashMap), "");
                cVar.e = 1;
                this.a.b.j = new a();
                this.a.b.j.addEventListener("URLLoader.Load.Complete", mVar);
                this.a.b.j.addEventListener("URLLoader.Load.Error", mVar);
                this.a.b.j.a(cVar);
            }
        } catch (Exception e) {
        }
    }
}
