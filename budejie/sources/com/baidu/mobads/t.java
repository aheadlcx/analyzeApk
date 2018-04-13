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
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).a(i);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).a(z);
        }
    }

    public void onLayoutComplete(int i, int i2) {
        if (SplashAd.b(this.e) == null) {
            float screenDensity = XAdSDKFoundationFacade.getInstance().getCommonUtils().getScreenDensity(this.a);
            if (((float) i) < 200.0f * screenDensity || ((float) i2) < screenDensity * 150.0f) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e(XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage(XAdErrorCode.SHOW_STANDARD_UNFIT, "开屏显示区域太小,宽度至少200dp,高度至少150dp"));
                SplashAd.a(this.e).onAdDismissed();
                return;
            }
            SplashAd.a(this.e, new a(this.a, this.b, this.c, this.d, i, i2));
            SplashAd.b(this.e).addEventListener("AdUserClick", SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_LOADED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_STARTED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_STOPPED, SplashAd.c(this.e));
            SplashAd.b(this.e).addEventListener(IXAdEvent.AD_ERROR, SplashAd.c(this.e));
            SplashAd.b(this.e).request();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).o();
        }
    }

    public void onAttachedToWindow() {
        if (SplashAd.b(this.e) != null) {
            SplashAd.b(this.e).n();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }
}
