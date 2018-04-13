package com.baidu.mobads;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.component.XAdView;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.g.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.h;

public class SplashAd {
    private a a;
    private volatile String b;
    private SplashAdListener c;
    private IOAdEventListener d;

    public SplashAd(Context context, ViewGroup viewGroup, SplashAdListener splashAdListener, String str) {
        this(context, viewGroup, splashAdListener, str, true);
    }

    public SplashAd(Context context, ViewGroup viewGroup, SplashAdListener splashAdListener, String str, boolean z) {
        this.b = "init";
        this.c = new q(this);
        this.d = new r(this);
        try {
            com.baidu.mobads.a.a.l = System.currentTimeMillis();
            com.baidu.mobads.a.a.m = 0;
            com.baidu.mobads.a.a.n = 0;
            com.baidu.mobads.a.a.o = 0;
            com.baidu.mobads.a.a.p = 0;
            com.baidu.mobads.a.a.q = 0;
            com.baidu.mobads.a.a.r = 0;
            a(viewGroup, context);
            if (splashAdListener != null) {
                this.c = splashAdListener;
            }
            if (TextUtils.isEmpty(str)) {
                this.c.onAdFailed("请您输入正确的广告位ID");
                return;
            }
            View xAdView = new XAdView(context);
            xAdView.setListener(new t(this, context, xAdView, str, z));
            xAdView.setLayoutParams(new LayoutParams(-1, -1));
            viewGroup.addView(xAdView);
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
            com.baidu.mobads.c.a.a().a("splash ad create failed: " + e.toString());
        }
    }

    public static void setAppSid(Context context, String str) {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().setAppId(str);
    }

    @Deprecated
    public static void setAppSec(Context context, String str) {
    }

    public void destroy() {
        if (this.a != null) {
            this.a.n();
        }
    }

    public static void setMaxVideoCacheCapacityMb(int i) {
        if (i < 15 || i > 100) {
            h.a(30);
            XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", "开屏设置视频最大缓存值有效范围在15~100M,默认30M", "");
            return;
        }
        h.a(i);
    }

    private void a(ViewGroup viewGroup, Context context) {
        try {
            viewGroup.addView(new SurfaceView(context), new RelativeLayout.LayoutParams(0, 0));
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
        }
    }
}
