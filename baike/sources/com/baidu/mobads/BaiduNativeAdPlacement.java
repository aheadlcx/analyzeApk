package com.baidu.mobads;

import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.lang.ref.WeakReference;

public class BaiduNativeAdPlacement {
    private String a;
    private IXAdResponseInfo b;
    private boolean c;
    private boolean d;
    private boolean e = false;
    private WeakReference<BaiduNativeH5AdView> f = null;
    private int g = 0;
    private int h = 1;
    private int i = 1;

    protected void a(boolean z) {
        this.e = z;
    }

    protected boolean a() {
        return this.e;
    }

    protected void a(BaiduNativeH5AdView baiduNativeH5AdView) {
        this.f = new WeakReference(baiduNativeH5AdView);
    }

    protected BaiduNativeH5AdView b() {
        if (this.f != null) {
            return (BaiduNativeH5AdView) this.f.get();
        }
        return null;
    }

    public void setApId(String str) {
        this.a = str;
    }

    public String getApId() {
        return this.a;
    }

    public static void setAppSid(String str) {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().setAppId(str);
    }

    public void setAdResponse(IXAdResponseInfo iXAdResponseInfo) {
        this.d = false;
        this.b = iXAdResponseInfo;
    }

    public boolean hasValidResponse() {
        return this.b != null && d();
    }

    protected IXAdResponseInfo c() {
        return this.b;
    }

    protected boolean d() {
        boolean z;
        if (this.b == null || this.b.getPrimaryAdInstanceInfo() == null) {
            z = false;
        } else if (System.currentTimeMillis() - this.b.getPrimaryAdInstanceInfo().getCreateTime() <= 1800000) {
            z = true;
        } else {
            z = false;
        }
        if (!z || this.c) {
            return false;
        }
        return true;
    }

    protected boolean e() {
        return this.d;
    }

    protected void b(boolean z) {
        this.c = z;
    }

    protected void c(boolean z) {
        this.d = z;
    }

    public boolean isAdDataLoaded() {
        BaiduNativeH5AdView b = b();
        if (b != null) {
            return b.a();
        }
        return false;
    }

    public void setSessionId(int i) {
        if (i >= 1) {
            this.g = i;
            this.i = g.a().a(i);
        }
    }

    protected int f() {
        return this.g;
    }

    public void setPositionId(int i) {
        if (i >= 1) {
            this.h = i;
        }
    }

    protected int g() {
        return this.h;
    }

    protected int h() {
        return this.i;
    }
}
