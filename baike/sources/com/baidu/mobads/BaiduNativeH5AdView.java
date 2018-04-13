package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.c.a;

public class BaiduNativeH5AdView extends RelativeLayout {
    IOAdEventListener a = new h(this);
    private BaiduNativeAdPlacement b;
    private a c;
    private BaiduNativeH5EventListner d = null;
    private RequestParameters e;
    private boolean f = false;
    private boolean g = false;

    public interface BaiduNativeH5EventListner {
        void onAdClick();

        void onAdDataLoaded();

        void onAdFail(String str);

        void onAdShow();
    }

    protected boolean a() {
        return this.g;
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, int i) {
        if (i != 0) {
            setBackgroundResource(i);
        }
    }

    protected BaiduNativeH5AdView(Context context, int i) {
        super(context);
        a(context, i);
    }

    public BaiduNativeAdPlacement getAdPlacement() {
        return this.b;
    }

    protected void setAdPlacement(BaiduNativeAdPlacement baiduNativeAdPlacement) {
        this.b = baiduNativeAdPlacement;
    }

    public void makeRequest(RequestParameters requestParameters) {
        if (this.b != null) {
            if (!this.b.hasValidResponse()) {
                this.f = false;
                if (!this.b.a()) {
                    this.b.a(true);
                } else {
                    return;
                }
            } else if (this.f) {
                return;
            }
        }
        if (requestParameters == null) {
            requestParameters = new Builder().build();
        }
        this.e = requestParameters;
        if (this.c != null) {
            c();
        }
        this.c = new a(getContext(), this);
        this.c.a(requestParameters);
        this.c.addEventListener(IXAdEvent.AD_ERROR, this.a);
        this.c.addEventListener(IXAdEvent.AD_STARTED, this.a);
        this.c.addEventListener("AdUserClick", this.a);
        this.c.addEventListener(IXAdEvent.AD_IMPRESSION, this.a);
        this.c.addEventListener("AdLoadData", this.a);
        if (!(this.b == null || this.b.c() == null)) {
            this.c.setAdResponseInfo(this.b.c());
        }
        this.c.b(this.b.f());
        this.c.c(this.b.g());
        this.c.d(this.b.h());
        this.c.request();
    }

    public void recordImpression() {
        if (this.b != null && this.b.c() != null && !this.b.e()) {
            this.c.a((View) this, this.b.c().getPrimaryAdInstanceInfo(), this.e);
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.o();
        }
    }

    private void c() {
        b();
        if (this.c != null) {
            this.c.n();
        }
    }

    public void setEventListener(BaiduNativeH5EventListner baiduNativeH5EventListner) {
        this.d = baiduNativeH5EventListner;
    }
}
