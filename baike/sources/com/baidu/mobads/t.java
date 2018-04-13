package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import com.baidu.mobads.component.XAdView;
import com.baidu.mobads.component.XAdView.Listener;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.production.g.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class t implements Listener {
    final /* synthetic */ Context a;
    final /* synthetic */ XAdView b;
    final /* synthetic */ String c;
    final /* synthetic */ boolean d;
    final /* synthetic */ SplashAd e;

    t(SplashAd splashAd, Context context, XAdView xAdView, String str, boolean z) {
        this.e = splashAd;
        this.a = context;
        this.b = xAdView;
        this.c = str;
        this.d = z;
    }

    public void onWindowVisibilityChanged(int i) {
        if (this.e.a != null) {
            this.e.a.a(i);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.e.a != null) {
            this.e.a.a(z);
        }
    }

    public void onLayoutComplete(int i, int i2) {
        if (this.e.a == null) {
            float screenDensity = XAdSDKFoundationFacade.getInstance().getCommonUtils().getScreenDensity(this.a);
            if (((float) i) < 200.0f * screenDensity || ((float) i2) < screenDensity * 150.0f) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e(XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage(XAdErrorCode.SHOW_STANDARD_UNFIT, "开屏显示区域太小,宽度至少200dp,高度至少150dp"));
                this.e.c.onAdDismissed();
                return;
            }
            this.e.a = new a(this.a, this.b, this.c, this.d, i, i2);
            this.e.a.addEventListener("AdUserClick", this.e.d);
            this.e.a.addEventListener(IXAdEvent.AD_LOADED, this.e.d);
            this.e.a.addEventListener(IXAdEvent.AD_STARTED, this.e.d);
            this.e.a.addEventListener(IXAdEvent.AD_STOPPED, this.e.d);
            this.e.a.addEventListener(IXAdEvent.AD_ERROR, this.e.d);
            this.e.a.request();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
        if (this.e.a != null) {
            this.e.a.m();
        }
    }

    public void onAttachedToWindow() {
        if (this.e.a != null) {
            this.e.a.l();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }
}
