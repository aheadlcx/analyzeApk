package com.baidu.mobads.production.g;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo$CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.b.f;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.production.u;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import com.baidu.mobads.utils.m;
import com.baidu.mobads.vo.XAdInstanceInfo;
import com.baidu.mobads.vo.d;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class a extends com.baidu.mobads.production.a {
    private String A;
    private String B;
    private Observer C = new c(this);
    protected final IXAdLogger w = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private e x;
    private Context y;
    private boolean z = false;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return q();
    }

    public a(Context context, RelativeLayout relativeLayout, String str, boolean z, int i, int i2) {
        String a;
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_SPLASH;
        this.y = context;
        this.x = new e(getApplicationContext(), this.o);
        this.x.a(z);
        g adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        if (z) {
            List arrayList = new ArrayList();
            arrayList.add(adConstants.getSupportedActionType4RequestingNone());
            arrayList.add(adConstants.getSupportedActionType4RequestingLandingPage());
            arrayList.add(adConstants.a());
            XAdSDKFoundationFacade.getInstance().getPackageUtils();
            if (m.b(context)) {
                arrayList.add(adConstants.getSupportedActionType4RequestingDownload());
            }
            a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a(arrayList);
        } else {
            a = adConstants.getSupportedActionType4RequestingNone();
        }
        this.x.b(a);
        this.x.d(i);
        this.x.e(i2);
        this.x.d(str);
        e(str);
    }

    protected void h() {
        this.m = 4200;
    }

    public void request() {
        com.baidu.mobads.a.a.m = System.currentTimeMillis();
        m();
        a(this.x);
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
            hashMap2.put("isRequestAndLoadAdTimeout", String.valueOf(this.z));
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            iXAdInstanceInfo = null;
            if (adResponseInfo != null) {
                iXAdInstanceInfo = adResponseInfo.getPrimaryAdInstanceInfo();
            }
            com.baidu.mobads.c.a.a().a(this.y, "386", iXAdInstanceInfo, this.x.d(), hashMap2);
        } catch (Throwable e) {
            this.w.e(e);
        }
        if (!this.z) {
            start();
            Handler handler = new Handler(this.y.getMainLooper());
            dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_LOADED));
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
            IXAdInstanceInfo$CreativeType creativeType = iXAdInstanceInfo.getCreativeType();
            if (creativeType == IXAdInstanceInfo$CreativeType.VIDEO || creativeType == IXAdInstanceInfo$CreativeType.RM) {
                com.baidu.mobads.c.a.a().a(this.y, "383", iXAdInstanceInfo, this.x.d(), "processAdLoaded");
                return;
            }
            a(handler);
        }
    }

    private void a(Handler handler) {
        handler.postDelayed(new b(this), 5000);
    }

    public void b() {
        IXAdInstanceInfo iXAdInstanceInfo = null;
        this.z = true;
        try {
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            if (adResponseInfo != null) {
                iXAdInstanceInfo = adResponseInfo.getPrimaryAdInstanceInfo();
            }
            com.baidu.mobads.c.a.a().a(this.y, "382", iXAdInstanceInfo, this.x.d(), null);
        } catch (Throwable e) {
            this.w.e(e);
        }
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            IXAdInstanceInfo iXAdInstanceInfo = (IXAdInstanceInfo) hashMap.get("AdInstance");
            IXAdInstanceInfo$CreativeType creativeType = iXAdInstanceInfo.getCreativeType();
            if (creativeType == IXAdInstanceInfo$CreativeType.VIDEO || creativeType == IXAdInstanceInfo$CreativeType.RM) {
                com.baidu.mobads.c.a.a().a(this.y, "383", iXAdInstanceInfo, this.x.d(), "processAdStart");
            }
        }
    }

    public d q() {
        return this.x;
    }

    public void g() {
        com.baidu.mobads.a.a.q = System.currentTimeMillis();
        if (this.h != null) {
            this.h.load();
        } else {
            this.w.e("container is null");
        }
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }

    public void a(boolean z, IXAdInstanceInfo iXAdInstanceInfo) {
        if (d(iXAdInstanceInfo)) {
            com.baidu.mobads.c.a.a().a(this.y, "383", iXAdInstanceInfo, this.x.d(), "file_exist_" + z);
            if (!z) {
                a("开屏因为请求到未在wifi下缓存的视频广告跳过");
            }
        }
    }

    public boolean a(IXAdInstanceInfo iXAdInstanceInfo) {
        return d(iXAdInstanceInfo);
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.e(iXAdContainer, hashMap);
        if (hashMap != null) {
            try {
                if (!TextUtils.isEmpty((String) hashMap.get("video_close_reason"))) {
                    com.baidu.mobads.c.a.a().a(this.y, "383", null, this.x.d(), "closead", r7);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String b(IXAdInstanceInfo iXAdInstanceInfo) {
        if (c(iXAdInstanceInfo)) {
            return iXAdInstanceInfo.getMainPictureUrl();
        }
        return super.b(iXAdInstanceInfo);
    }

    public boolean e(IXAdInstanceInfo iXAdInstanceInfo) {
        return c(iXAdInstanceInfo) || e();
    }

    public boolean d() {
        return true;
    }

    protected void a() {
        if (e()) {
            this.A = XAdSDKFoundationFacade.getInstance().getIoUtils().getStoreagePath(this.y);
            this.B = XAdSDKFoundationFacade.getInstance().getCommonUtils().md5("http://mobads.baidu.com/ads/img/3d_bg.jpg");
            File file = new File(this.A + this.B);
            try {
                URL url = new URL("http://mobads.baidu.com/ads/img/3d_bg.jpg");
                if (a(file, url)) {
                    ((XAdInstanceInfo) this.d).setSplash3DLocalUrl(null);
                    IOAdDownloader fVar = new f(this.y, url, this.A, this.B, false);
                    fVar.addObserver(this.C);
                    fVar.start();
                    return;
                }
                f(this.A + this.B);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void f(String str) {
        ((XAdInstanceInfo) this.d).setSplash3DLocalUrl(str);
        if (e() && TextUtils.isEmpty(((XAdInstanceInfo) this.d).getLocalCreativeURL())) {
            this.w.e("zip pic no download");
        } else {
            b("splash back pic ready");
        }
    }

    private boolean a(File file, URL url) {
        if (file.exists()) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                int parseInt = Integer.parseInt(httpURLConnection.getHeaderField("content-length"));
                if (parseInt > 0 && file.length() == ((long) parseInt)) {
                    httpURLConnection.disconnect();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
