package com.baidu.mobads.g;

import android.os.Build;
import android.os.Build.VERSION;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
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
                hashMap.put(IXAdRequestInfo.V, "" + d);
                hashMap.put("os", AlibcConstants.PF_ANDROID);
                hashMap.put(IXAdRequestInfo.PHONE_TYPE, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(Build.MODEL));
                hashMap.put(IXAdRequestInfo.BDR, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(VERSION.SDK));
                cVar = new c(uRIUitls.addParameters(g.j(), hashMap), "");
                cVar.e = 1;
                g.a(this.a.b, new a());
                g.i(this.a.b).addEventListener("URLLoader.Load.Complete", mVar);
                g.i(this.a.b).addEventListener("URLLoader.Load.Error", mVar);
                g.i(this.a.b).a(cVar);
            } else {
                mVar = new m(this, d);
                hashMap = new HashMap();
                hashMap.put(IXAdRequestInfo.V, "" + d);
                hashMap.put("os", AlibcConstants.PF_ANDROID);
                hashMap.put(IXAdRequestInfo.PHONE_TYPE, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(Build.MODEL));
                hashMap.put(IXAdRequestInfo.BDR, XAdSDKFoundationFacade.getInstance().getCommonUtils().getTextEncoder(VERSION.SDK));
                cVar = new c(uRIUitls.addParameters(g.j(), hashMap), "");
                cVar.e = 1;
                g.a(this.a.b, new a());
                g.i(this.a.b).addEventListener("URLLoader.Load.Complete", mVar);
                g.i(this.a.b).addEventListener("URLLoader.Load.Error", mVar);
                g.i(this.a.b).a(cVar);
            }
        } catch (Exception e) {
        }
    }
}
