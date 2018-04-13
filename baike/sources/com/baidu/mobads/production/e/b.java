package com.baidu.mobads.production.e;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class b extends a implements a {
    private f A;
    private boolean B = false;
    private boolean C = false;
    private Activity D;
    private Boolean E;
    public final String v = "html5_intersitial";
    protected final IXAdLogger w = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private RelativeLayout x;
    private TextView y;
    private CountDownTimer z;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return r();
    }

    public b(Context context, RelativeLayout relativeLayout, Boolean bool, AdSize adSize, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_INTERSTITIAL;
        this.E = bool;
        this.A = new f(getApplicationContext(), getActivity(), this.o, Boolean.valueOf(true));
        this.A.c(SlotType.SLOT_TYPE_INTERSTITIAL.getValue());
        this.A.f(adSize.getValue());
        this.A.d(str);
        d(str);
    }

    public void e() {
    }

    protected void f() {
        this.m = 8000;
    }

    public void request() {
        super.a(this.A);
    }

    protected void a(c cVar, u uVar, int i) {
        uVar.a(cVar, "{'ad':[{'id':99999999,'url':'" + this.A.b() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}");
    }

    public void start() {
        super.start();
    }

    public void o() {
    }

    public void a(int i, int i2) {
        if (!this.B && !this.C) {
            this.A.d(i);
            this.A.e(i2);
            load();
        }
    }

    public void a(Activity activity) {
    }

    public void a(Activity activity, RelativeLayout relativeLayout) {
        try {
            this.w.d("showInterstitialAdInit");
            if (this.B && !this.C) {
                this.C = true;
                this.B = false;
                this.D = activity;
                start();
                s();
                this.e.setBackgroundColor(0);
                View relativeLayout2 = new RelativeLayout(activity);
                relativeLayout2.setBackgroundColor(0);
                relativeLayout.addView(relativeLayout2, new LayoutParams(-1, -1));
                this.e.addView(this.h.getAdView(), new LayoutParams(-1, -1));
                relativeLayout2.addView(this.e, new LayoutParams(-1, -1));
                this.h.getAdView().setVisibility(4);
            } else if (this.C) {
                this.w.w("interstitial ad is showing now");
            } else if (!this.B) {
                this.w.w("interstitial ad is not ready");
            }
        } catch (Throwable e) {
            this.w.d(e);
        }
    }

    public void p() {
        new Handler(Looper.getMainLooper()).post(new c(this));
    }

    private boolean u() {
        return q();
    }

    protected boolean q() {
        return AdSize.InterstitialForVideoBeforePlay.getValue() == this.A.getApt();
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.B = true;
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        p();
    }

    public d r() {
        return this.A;
    }

    protected void s() {
        if (this.D != null) {
            this.D.runOnUiThread(new d(this));
        }
    }

    public boolean t() {
        return this.B;
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        s();
        this.C = false;
    }

    private View v() {
        if (this.x == null) {
            this.x = new RelativeLayout(this.f);
            this.x.setBackgroundColor(Color.argb(42, 0, 0, 0));
            this.y = new TextView(this.f);
            this.y.setTextColor(-65536);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.x.addView(this.y, layoutParams);
        }
        this.z = new e(this, 6000, 1000).start();
        return this.x;
    }

    private void w() {
        if (!(this.x == null || this.x.getParent() == null)) {
            ((ViewGroup) this.x.getParent()).removeView(this.x);
        }
        if (this.z != null) {
            this.w.d("cancel countDownTimer before it finished");
            try {
                this.z.cancel();
            } catch (Throwable e) {
                this.w.d(e);
            }
        }
    }

    private LayoutParams x() {
        int screenDensity = (int) (20.0f * XAdSDKFoundationFacade.getInstance().getCommonUtils().getScreenDensity(this.f));
        LayoutParams layoutParams = new LayoutParams(screenDensity, screenDensity);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        return layoutParams;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return true;
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
