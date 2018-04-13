package com.baidu.mobads;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.baidu.mobads.component.XAdView;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.a.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AdView extends RelativeLayout {
    IOAdEventListener a;
    private AtomicBoolean b;
    private a c;
    private AdViewListener d;

    public AdView(Context context) {
        super(context);
        this.b = new AtomicBoolean(false);
        this.a = new a(this);
    }

    public AdView(Context context, String str) {
        this(context, true, AdSize.Banner, str);
    }

    public AdView(Context context, AdSize adSize, String str) {
        this(context, true, adSize, str);
    }

    AdView(Context context, boolean z, AdSize adSize, String str) {
        this(context, null, z, adSize, str);
    }

    public AdView(Context context, AttributeSet attributeSet, boolean z, AdSize adSize, String str) {
        super(context, attributeSet);
        this.b = new AtomicBoolean(false);
        this.a = new a(this);
        View xAdView = new XAdView(context);
        this.c = new a(context, xAdView, str, z);
        this.c.addEventListener(IXAdEvent.AD_LOADED, this.a);
        this.c.addEventListener(IXAdEvent.AD_ERROR, this.a);
        this.c.addEventListener(IXAdEvent.AD_STARTED, this.a);
        this.c.addEventListener("AdUserClick", this.a);
        this.c.addEventListener(IXAdEvent.AD_USER_CLOSE, this.a);
        xAdView.setListener(new c(this));
        addView(xAdView, new LayoutParams(-1, -1));
    }

    private void a() {
        if (!this.b.get()) {
            this.b.set(true);
            this.c.request();
        }
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("AdView.setLayoutParams=", Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        int i = layoutParams.width;
        int i2 = layoutParams.height;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("AdView.setLayoutParams", Integer.valueOf(i3), Integer.valueOf(i4), Float.valueOf(f));
        if (i <= 0) {
            i = Math.min(i3, i4);
        } else if (i > 0 && ((float) i) < 200.0f * f) {
            i = (int) (200.0f * f);
        }
        int min = i2 <= 0 ? (int) (((float) Math.min(i3, i4)) * 0.15f) : (i2 <= 0 || ((float) i2) >= 30.0f * f) ? i2 : (int) (30.0f * f);
        layoutParams.width = i;
        layoutParams.height = min;
        if (!(this.c == null || this.c.o() == null)) {
            this.c.o().d(layoutParams.width);
            this.c.o().e(layoutParams.height);
        }
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("AdView.setLayoutParams adapter", Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height));
        super.setLayoutParams(layoutParams);
    }

    public void setListener(AdViewListener adViewListener) {
        this.d = adViewListener;
    }

    @Deprecated
    public void setAlpha(float f) {
    }

    @Deprecated
    public void setBackgroundColor(int i) {
    }

    public void destroy() {
        this.c.n();
    }

    public static void setAppSid(Context context, String str) {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().setAppId(str);
    }

    @Deprecated
    public static void setAppSec(Context context, String str) {
    }
}
