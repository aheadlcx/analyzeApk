package com.baidu.mobads.production.g;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import com.baidu.mobads.vo.d;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a extends com.baidu.mobads.production.a {
    protected final IXAdLogger v = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private e w;
    private Context x;
    private boolean y = false;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return o();
    }

    public a(Context context, RelativeLayout relativeLayout, String str, boolean z, int i, int i2) {
        String a;
        super(context);
        new Thread(new b(this)).start();
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_SPLASH;
        this.x = context;
        this.w = new e(getApplicationContext(), this.o);
        this.w.a(z);
        g adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        if (z) {
            List arrayList = new ArrayList();
            arrayList.add(adConstants.getSupportedActionType4RequestingNone());
            arrayList.add(adConstants.getSupportedActionType4RequestingLandingPage());
            arrayList.add(adConstants.a());
            if (XAdSDKFoundationFacade.getInstance().getPackageUtils().a(context)) {
                arrayList.add(adConstants.getSupportedActionType4RequestingDownload());
            }
            a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a(arrayList);
        } else {
            a = adConstants.getSupportedActionType4RequestingNone();
        }
        this.w.b(a);
        this.w.d(i);
        this.w.e(i2);
        this.w.d(str);
        d(str);
    }

    protected void f() {
        this.m = 4200;
    }

    public void request() {
        com.baidu.mobads.a.a.m = System.currentTimeMillis();
        k();
        a(this.w);
        try {
            new WebView(getActivity()).loadDataWithBaseURL(null, "", "text/html", "UTF-8", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(c cVar, u uVar, int i) {
        uVar.a(cVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        IXAdInstanceInfo iXAdInstanceInfo;
        com.baidu.mobads.a.a.r = System.currentTimeMillis();
        try {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("m_new_rsplash", String.valueOf(com.baidu.mobads.a.a.l));
            hashMap2.put("m_start_request", String.valueOf(com.baidu.mobads.a.a.m));
            hashMap2.put("m_end_request", String.valueOf(com.baidu.mobads.a.a.n));
            hashMap2.put("m_start_dex", String.valueOf(com.baidu.mobads.a.a.o));
            hashMap2.put("m_end_dex", String.valueOf(com.baidu.mobads.a.a.p));
            hashMap2.put("m_start_load", String.valueOf(com.baidu.mobads.a.a.q));
            hashMap2.put("m_end_load", String.valueOf(com.baidu.mobads.a.a.r));
            hashMap2.put("isRequestAndLoadAdTimeout", String.valueOf(this.y));
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            iXAdInstanceInfo = null;
            if (adResponseInfo != null) {
                iXAdInstanceInfo = adResponseInfo.getPrimaryAdInstanceInfo();
            }
            com.baidu.mobads.c.a.a().a(this.x, "386", iXAdInstanceInfo, this.w.d(), hashMap2);
        } catch (Throwable e) {
            this.v.e(e);
        }
        if (!this.y) {
            start();
            Handler handler = new Handler(this.x.getMainLooper());
            handler.post(new c(this));
            if (hashMap == null) {
                try {
                    a(handler);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            iXAdInstanceInfo = (IXAdInstanceInfo) hashMap.get("AdInstance");
            CreativeType creativeType = iXAdInstanceInfo.getCreativeType();
            if (creativeType == CreativeType.VIDEO || creativeType == CreativeType.RM) {
                com.baidu.mobads.c.a.a().a(this.x, "383", iXAdInstanceInfo, this.w.d(), "processAdLoaded");
                return;
            }
            a(handler);
        }
    }

    private void a(Handler handler) {
        handler.postDelayed(new d(this), Config.BPLUS_DELAY_TIME);
    }

    public void a() {
        IXAdInstanceInfo iXAdInstanceInfo = null;
        this.y = true;
        try {
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            if (adResponseInfo != null) {
                iXAdInstanceInfo = adResponseInfo.getPrimaryAdInstanceInfo();
            }
            com.baidu.mobads.c.a.a().a(this.x, "382", iXAdInstanceInfo, this.w.d(), null);
        } catch (Throwable e) {
            this.v.e(e);
        }
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            IXAdInstanceInfo iXAdInstanceInfo = (IXAdInstanceInfo) hashMap.get("AdInstance");
            CreativeType creativeType = iXAdInstanceInfo.getCreativeType();
            if (creativeType == CreativeType.VIDEO || creativeType == CreativeType.RM) {
                com.baidu.mobads.c.a.a().a(this.x, "383", iXAdInstanceInfo, this.w.d(), "processAdStart");
            }
        }
    }

    public d o() {
        return this.w;
    }

    public void e() {
        com.baidu.mobads.a.a.q = System.currentTimeMillis();
        if (this.h != null) {
            this.h.load();
        } else {
            this.v.e("container is null");
        }
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }

    public void a(boolean z, IXAdInstanceInfo iXAdInstanceInfo) {
        com.baidu.mobads.c.a.a().a(this.x, "383", iXAdInstanceInfo, this.w.d(), "file_exist_" + z);
        if (!z) {
            a("开屏因为请求到未在wifi下缓存的视频广告跳过");
        }
    }

    public boolean c() {
        return true;
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.e(iXAdContainer, hashMap);
        if (hashMap != null) {
            try {
                if (!TextUtils.isEmpty((String) hashMap.get("video_close_reason"))) {
                    com.baidu.mobads.c.a.a().a(this.x, "383", null, this.w.d(), "closead", r7);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
