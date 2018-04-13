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

    protected void setRequestStarted(boolean z) {
        this.e = z;
    }

    protected boolean getRequestStarted() {
        return this.e;
    }

    protected void setAdView(BaiduNativeH5AdView baiduNativeH5AdView) {
        this.f = new WeakReference(baiduNativeH5AdView);
    }

    protected BaiduNativeH5AdView getAdView() {
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
        return this.b != null && isAdAvailable();
    }

    protected IXAdResponseInfo getAdResponse() {
        return this.b;
    }

    protected boolean isAdAvailable() {
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

    protected boolean isWinSended() {
        return this.d;
    }

    protected void setClicked(boolean z) {
        this.c = z;
    }

    protected void setWinSended(boolean z) {
        this.d = z;
    }

    public boolean isAdDataLoaded() {
        BaiduNativeH5AdView adView = getAdView();
        if (adView != null) {
            return adView.isAdDataLoaded();
        }
        return false;
    }

    public void setSessionId(int i) {
        if (i >= 1) {
            this.g = i;
            this.i = g.a().a(i);
        }
    }

    protected int getSessionId() {
        return this.g;
    }

    public void setPositionId(int i) {
        if (i >= 1) {
            this.h = i;
        }
    }

    protected int getPosistionId() {
        return this.h;
    }

    protected int getSequenceId() {
        return this.i;
    }
}
