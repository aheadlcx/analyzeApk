package com.baidu.mobads.production;

import com.baidu.mobads.c.a;
import com.baidu.mobads.g.g$c;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.utils.l;

class j implements g$c {
    final /* synthetic */ a a;

    j(a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
        if (z) {
            try {
                if (BaiduXAdSDKContext.mApkLoader != null) {
                    a.a = BaiduXAdSDKContext.mApkLoader.h();
                    if (a.a != null) {
                        this.a.r();
                        return;
                    }
                }
            } catch (Throwable e) {
                l.a().e(e);
                a.a().a("async apk on load exception: " + e.toString());
                return;
            }
        }
        BaiduXAdSDKContext.mApkLoader = null;
        this.a.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
    }
}
