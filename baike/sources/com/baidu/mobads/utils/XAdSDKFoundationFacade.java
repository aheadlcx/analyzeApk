package com.baidu.mobads.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.baidu.mobads.e.a;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.error.IXAdErrorCode;
import com.baidu.mobads.interfaces.utils.IBase64;
import com.baidu.mobads.interfaces.utils.IXAdActivityUtils;
import com.baidu.mobads.interfaces.utils.IXAdBitmapUtils;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.interfaces.utils.IXAdViewUtils;
import com.baidu.mobads.openad.b.b;
import com.baidu.mobads.openad.b.d;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloaderManager;
import java.util.HashMap;

public class XAdSDKFoundationFacade {
    private static final XAdSDKFoundationFacade o = new XAdSDKFoundationFacade();
    private h a;
    private n b = new n();
    private IBase64 c = new a();
    private IXAdLogger d = l.a();
    private IXAdViewUtils e = new t();
    private IXAdBitmapUtils f = new c();
    private IXAdURIUitls g = new s();
    private IXAdIOUtils h = new k();
    private m i = new m();
    private IXAdActivityUtils j = new b();
    private IXAdSystemUtils k = new o();
    private d l = new d();
    private g m = new g();
    private IXAdErrorCode n = new a(this.d);
    private Context p;
    private IXAdContainerFactory q;

    public static XAdSDKFoundationFacade getInstance() {
        return o;
    }

    private XAdSDKFoundationFacade() {
    }

    public void initializeApplicationContext(Context context) {
        if (this.p == null) {
            this.p = context;
        }
        this.a = new h(this.p);
    }

    public void initializeAdContainerFactory(IXAdContainerFactory iXAdContainerFactory) {
        if (iXAdContainerFactory == null) {
            this.q = iXAdContainerFactory;
        }
    }

    public h getAdCreativeCacheManager() {
        return this.a;
    }

    public IXAdContainerFactory getAdContainerFactory() {
        return this.q;
    }

    public Context getApplicationContext() {
        return this.p;
    }

    public IBase64 getBase64() {
        return this.c;
    }

    public IXAdLogger getAdLogger() {
        return this.d;
    }

    public n getAdResource() {
        return this.b;
    }

    public IXAdBitmapUtils getBitmapUtils() {
        return this.f;
    }

    public IXAdURIUitls getURIUitls() {
        return this.g;
    }

    public IXAdViewUtils getViewUtils() {
        return this.e;
    }

    public IXAdIOUtils getIoUtils() {
        return this.h;
    }

    public m getPackageUtils() {
        return this.i;
    }

    public d getCommonUtils() {
        return this.l;
    }

    public IXAdSystemUtils getSystemUtils() {
        return this.k;
    }

    public IXAdActivityUtils getActivityUtils() {
        return this.j;
    }

    public g getAdConstants() {
        return this.m;
    }

    public IXAdErrorCode getErrorCode() {
        return this.n;
    }

    public IOAdDownloaderManager getDownloaderManager(Context context) {
        return d.a(context);
    }

    public IOAdDownloaderManager getDownloaderManager() {
        return d.a(getApplicationContext());
    }

    public void setMobileConfirmed(String str) {
        b a = b.a(str);
        if (a != null) {
            com.baidu.mobads.command.a a2 = a.a();
            if (a2 != null) {
                a2.s = true;
            }
        }
    }

    public void makeRequest(String str) {
        c cVar = new c(str, "");
        cVar.e = 1;
        new com.baidu.mobads.openad.d.a().a(cVar);
    }

    public String getProxyVer() {
        return "8.7004";
    }

    public void downloadApp(IXAdInstanceInfo iXAdInstanceInfo) {
        try {
            getInstance().getCommonUtils();
        } catch (Exception e) {
            Log.e("", "");
        }
    }

    public void downloadAppSilence(IXAdInstanceInfo iXAdInstanceInfo) {
        downloadApp(iXAdInstanceInfo);
    }

    public void sendLog(String str, HashMap<String, String> hashMap) {
        com.baidu.mobads.c.a.a().a(getApplicationContext(), str, null, null, (HashMap) hashMap);
    }

    public Intent getInstallIntent(String str) {
        return getPackageUtils().a(getApplicationContext(), str);
    }
}
