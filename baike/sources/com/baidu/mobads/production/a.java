package com.baidu.mobads.production;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.baidu.mobads.g.g;
import com.baidu.mobads.g.q;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.download.activate.IXActivateListener;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.c.c;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.h;
import com.baidu.mobads.vo.d;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a extends c implements IXNonLinearAdSlot {
    public static IXAdContainerFactory a;
    private static final String[] v = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private IOAdEventListener A = new b(this);
    protected Boolean b = Boolean.valueOf(false);
    public IXAdInstanceInfo d = null;
    protected RelativeLayout e;
    protected Context f;
    protected int g = 0;
    public IXAdContainer h;
    protected String i;
    protected u j;
    protected d k;
    protected SlotState l = SlotState.IDEL;
    protected int m = 5000;
    protected int n = 0;
    protected SlotType o;
    protected HashMap<String, String> p = new HashMap();
    protected AtomicBoolean q = new AtomicBoolean();
    protected final IXAdLogger r = XAdSDKFoundationFacade.getInstance().getAdLogger();
    protected long s;
    protected long t;
    protected long u;
    private IXAdResponseInfo w;
    private String x;
    private Handler y = new Handler(Looper.getMainLooper());
    private Runnable z = null;

    private static class a implements IXActivateListener {
        private Context a;

        public a(Context context) {
            this.a = context.getApplicationContext();
        }

        public void onAppActivation(IXAppInfo iXAppInfo) {
            com.baidu.mobads.c.a.a().b(this.a, iXAppInfo);
        }

        public void onAppInstalled(IXAppInfo iXAppInfo) {
            com.baidu.mobads.c.a.a().a(this.a, iXAppInfo);
        }
    }

    protected abstract void a(com.baidu.mobads.openad.d.c cVar, u uVar, int i);

    protected abstract void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    public abstract void c(IXAdResponseInfo iXAdResponseInfo);

    protected abstract void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    public abstract void e();

    protected abstract void f();

    public a(Context context) {
        new Thread(new f(this, context)).start();
    }

    private void c(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new g(this, context), 2000);
    }

    public SlotState getSlotState() {
        return this.l;
    }

    public void setActivity(Context context) {
        this.f = context;
        this.s = System.currentTimeMillis();
        b();
        this.q.set(false);
        f();
        com.baidu.mobads.c.a.a().a(getApplicationContext());
        XAdSDKFoundationFacade.getInstance().initializeApplicationContext(getApplicationContext());
        this.z = new i(this);
        b(this.f);
        q.a(this.f).a();
    }

    public void a() {
    }

    public void setAdSlotBase(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public void setId(String str) {
        this.x = str;
    }

    protected void b() {
    }

    public String getId() {
        return this.x;
    }

    public SlotType getType() {
        return this.o;
    }

    protected void a(XAdErrorCode xAdErrorCode, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msg", xAdErrorCode);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage(xAdErrorCode, str);
    }

    protected void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("error_message", str);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
    }

    protected void a(Context context) {
        if (BaiduXAdSDKContext.mApkLoader == null) {
            synchronized (g.class) {
                if (BaiduXAdSDKContext.mApkLoader == null) {
                    BaiduXAdSDKContext.mApkLoader = new g(context.getApplicationContext());
                }
            }
        }
        if (a != null) {
            o();
        } else {
            BaiduXAdSDKContext.mApkLoader.a(new j(this));
        }
    }

    private void o() {
        BaiduXAdSDKContext.isRemoteLoadSuccess = Boolean.valueOf(true);
        b("XAdMouldeLoader load success");
    }

    protected void b(String str) {
        this.r.i("XAbstractAdProdTemplate", "doubleCheck:" + str + ", bfp=" + this.b + ", apk=" + BaiduXAdSDKContext.isRemoteLoadSuccess);
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue()) {
            XAdSDKFoundationFacade.getInstance().initializeAdContainerFactory(getAdContainerFactory());
        }
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue() && this.b.booleanValue()) {
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            if (adResponseInfo != null) {
                try {
                    IXAdInstanceInfo primaryAdInstanceInfo = adResponseInfo.getPrimaryAdInstanceInfo();
                    if (c() && (primaryAdInstanceInfo.getCreativeType() == CreativeType.VIDEO || primaryAdInstanceInfo.getCreativeType() == CreativeType.RM)) {
                        String videoUrl = primaryAdInstanceInfo.getVideoUrl();
                        if (primaryAdInstanceInfo.getCreativeType() == CreativeType.RM) {
                            videoUrl = primaryAdInstanceInfo.getMainPictureUrl();
                        }
                        videoUrl = h.a(getApplicationContext(), videoUrl);
                        boolean exists = new File(videoUrl).exists();
                        a(exists, primaryAdInstanceInfo);
                        if (exists) {
                            XAdSDKFoundationFacade.getInstance().getAdCreativeCacheManager().c(videoUrl);
                            primaryAdInstanceInfo.setLocalCreativeURL(videoUrl);
                            b(adResponseInfo);
                        }
                        d(adResponseInfo);
                        return;
                    }
                    d(adResponseInfo);
                    try {
                        b(adResponseInfo);
                    } catch (Throwable e) {
                        this.r.i("XAbstractAdProdTemplate", e);
                    }
                } catch (Throwable e2) {
                    this.r.i("XAbstractAdProdTemplate", e2);
                }
            } else {
                this.r.i("XAbstractAdProdTemplate", "doubleCheck IXAdResponseInfo is null, but isBFP4APPRequestSuccess is true");
            }
        }
    }

    public boolean c() {
        return false;
    }

    private void d(IXAdResponseInfo iXAdResponseInfo) {
        String videoUrl;
        this.r.i("XAbstractAdProdTemplate", "try2CachingVideoAdCreativeAsset");
        IXAdInstanceInfo primaryAdInstanceInfo = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        String str = "";
        if (primaryAdInstanceInfo.getCreativeType() == CreativeType.VIDEO) {
            videoUrl = primaryAdInstanceInfo.getVideoUrl();
        } else if (primaryAdInstanceInfo.getCreativeType() == CreativeType.RM) {
            videoUrl = primaryAdInstanceInfo.getMainPictureUrl();
        } else {
            videoUrl = str;
        }
        if (!XAdSDKFoundationFacade.getInstance().getSystemUtils().isWifiConnected(getApplicationContext()).booleanValue()) {
            com.baidu.mobads.c.a.a().a(this.f, "383", primaryAdInstanceInfo, this.k.d(), "file_dl_failed_not_wifi", videoUrl);
        } else if (!TextUtils.isEmpty(videoUrl)) {
            primaryAdInstanceInfo.setLocalCreativeURL(null);
            String a = h.a(getApplicationContext());
            String b = h.b(videoUrl);
            h adCreativeCacheManager = XAdSDKFoundationFacade.getInstance().getAdCreativeCacheManager();
            adCreativeCacheManager.a(a);
            adCreativeCacheManager.a(getApplicationContext(), videoUrl, a, b, new k(this, Looper.getMainLooper(), primaryAdInstanceInfo));
        }
    }

    private void a(String str, Uri uri) {
        new Thread(new l(this, str, uri)).start();
    }

    public void a(boolean z, IXAdInstanceInfo iXAdInstanceInfo) {
    }

    protected boolean a(d dVar) {
        this.r.i("XAbstractAdProdTemplate", "doRequest()");
        new Thread(new m(this, dVar)).start();
        return true;
    }

    protected void b(d dVar) {
        this.k = dVar;
        i();
        this.b = Boolean.valueOf(false);
        String b = this.i == null ? dVar.b() : this.i;
        this.j = new u();
        com.baidu.mobads.c.a.b = b;
        com.baidu.mobads.openad.d.c cVar = new com.baidu.mobads.openad.d.c(b, "");
        cVar.e = 1;
        this.j.addEventListener("URLLoader.Load.Complete", this.A);
        this.j.addEventListener("URLLoader.Load.Error", this.A);
        a(cVar, this.j, this.m);
    }

    protected void a(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        j();
        c(iXAdContainer, hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_LOADED));
    }

    protected void b(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.l = SlotState.PLAYING;
        d(iXAdContainer, hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STARTED));
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo) {
        c(iXAdResponseInfo);
    }

    protected void c(String str) {
        com.baidu.mobads.c.a.a().a(str);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
    }

    protected void b(IXAdResponseInfo iXAdResponseInfo) {
        this.r.i("XAbstractAdProdTemplate", "handleAllReady");
        this.g++;
        this.d = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        Context applicationContext = getApplicationContext();
        IXAdContainerContext oVar = new o(applicationContext, getActivity(), this.k.d(), this.e, new p(applicationContext, this), iXAdResponseInfo, null);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a(oVar);
        } else {
            new Handler(Looper.getMainLooper()).post(new n(this, oVar));
        }
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo, IXAdInstanceInfo iXAdInstanceInfo) {
        this.d = iXAdInstanceInfo;
    }

    public Context getApplicationContext() {
        Activity activity = getActivity();
        return activity == null ? this.f : activity.getApplicationContext();
    }

    public Activity getActivity() {
        if (this.f instanceof Activity) {
            return (Activity) this.f;
        }
        if (this.e == null || !(this.e.getContext() instanceof Activity)) {
            return null;
        }
        return (Activity) this.e.getContext();
    }

    public IXAdContainer getCurrentXAdContainer() {
        return this.h;
    }

    public IXAdContainerFactory getAdContainerFactory() {
        return a;
    }

    public static IXAdContainerFactory d() {
        return a;
    }

    protected void a(IXAdContainerContext iXAdContainerContext) {
        try {
            this.r.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread()");
            this.t = System.currentTimeMillis();
            this.h = b(iXAdContainerContext);
            this.u = System.currentTimeMillis();
            if (this.h == null) {
                this.r.e("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer is null");
                dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STOPPED));
                return;
            }
            this.r.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer be created");
            this.p.put("start", "" + this.s);
            this.p.put("container_before_created", "" + this.t);
            this.p.put("container_after_created", "" + this.u);
            this.h.setParameters(this.p);
            com.baidu.mobads.a.a.c = this.h.getRemoteVersion();
            this.r.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer be created, hasCalledLoadAtAppSide=" + this.q.get());
            if (this.q.get()) {
                this.h.load();
            }
            e();
            c(this.f);
        } catch (Exception e) {
            e.printStackTrace();
            this.r.e(XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage(XAdErrorCode.PERMISSION_PROBLEM, e.getMessage()));
            com.baidu.mobads.c.a.a().a("process all ready on UI Thread exception: " + e.toString());
        }
    }

    private IXAdContainer b(IXAdContainerContext iXAdContainerContext) {
        IXAdContainer iXAdContainer = null;
        this.r.i("XAbstractAdProdTemplate", "createAdContainer");
        if (a != null) {
            iXAdContainer = a.createXAdContainer(iXAdContainerContext, null);
            if (iXAdContainer != null) {
                this.r.i("XAbstractAdProdTemplate", "createAdContainer() apk.version=" + a.getRemoteVersion());
            }
        }
        return iXAdContainer;
    }

    public IXAdProdInfo getProdInfo() {
        return this.k.d();
    }

    public void setParameter(HashMap<String, String> hashMap) {
        this.p = hashMap;
    }

    public HashMap<String, String> getParameter() {
        return this.p;
    }

    public int getDuration() {
        return -1;
    }

    public int getPlayheadTime() {
        return -1;
    }

    public IXAdInstanceInfo getCurrentAdInstance() {
        return this.d;
    }

    public ViewGroup getProdBase() {
        return this.e;
    }

    public void load() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.q.set(true);
        }
    }

    public void resize() {
        if (this.h != null && getApplicationContext() != null) {
            new Handler(getApplicationContext().getMainLooper()).post(new c(this));
        }
    }

    public void pause() {
        g();
    }

    protected void g() {
        if (this.h != null && getApplicationContext() != null) {
            this.l = SlotState.PAUSED;
            new Handler(getApplicationContext().getMainLooper()).post(new d(this));
        }
    }

    public void start() {
        if (this.h != null) {
            this.h.start();
        }
    }

    public void resume() {
        h();
    }

    protected void h() {
        if (this.h != null && getApplicationContext() != null) {
            this.l = SlotState.PLAYING;
            new Handler(getApplicationContext().getMainLooper()).post(new e(this));
        }
    }

    public void stop() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i("XAbstractAdProdTemplate", "stop");
        if (this.h != null) {
            this.h.stop();
            this.h = null;
        }
    }

    protected void i() {
        if (this.j != null) {
            this.j.removeAllListeners();
            this.j.a();
        }
    }

    protected void j() {
        if (this.z != null) {
            this.y.removeCallbacks(this.z);
        }
        this.z = null;
    }

    protected void k() {
        if (this.z != null) {
            this.y.postDelayed(this.z, (long) this.m);
        }
    }

    public void l() {
        if (this.h != null) {
            this.h.onAttachedToWindow();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void m() {
        if (this.h != null) {
            this.h.onDetachedFromWindow();
        }
    }

    public void a(int i) {
        if (this.h != null) {
            this.h.onWindowVisibilityChanged(i);
        }
    }

    public void a(boolean z) {
        if (this.h != null) {
            this.h.onWindowFocusChanged(z);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.h != null) {
            return this.h.processKeyEvent(i, keyEvent).booleanValue();
        }
        return false;
    }

    protected void d(String str) {
        if (TextUtils.isEmpty(str)) {
            this.r.e("代码位id(adPlaceId)不可以为空");
        }
    }

    public void b(Context context) {
        try {
            com.baidu.mobads.utils.d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            a(context, "android.permission.INTERNET");
            a(context, "android.permission.ACCESS_NETWORK_STATE");
            if (commonUtils.isOldPermissionModel() || context.getApplicationInfo().targetSdkVersion < 23) {
                a(context, "android.permission.READ_PHONE_STATE");
                a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
                return;
            }
            int i;
            ArrayList arrayList = new ArrayList();
            for (i = 0; i < v.length; i++) {
                if (!commonUtils.checkSelfPermission(context, v[i])) {
                    arrayList.add(v[i]);
                }
            }
            i = arrayList.size();
            if (i > 0) {
                commonUtils.a(context, (String[]) arrayList.toArray(new String[i]), 1234323329);
            }
        } catch (Exception e) {
            this.r.e(XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage(XAdErrorCode.PERMISSION_PROBLEM, e.getMessage()));
            com.baidu.mobads.c.a.a().a("check permission exception: " + e.toString());
        }
    }

    public void a(Context context, String str) {
        if (!XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, str)) {
            String str2 = "Cannot request an ad without necessary permissions!  Open manifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"" + str + "\" />";
            this.r.e("BaiduMobAds SDK", str2);
            throw new SecurityException(str2);
        }
    }

    public void n() {
        if (this.h != null) {
            this.h.destroy();
        }
        BaiduXAdSDKContext.exit();
    }

    public IXAdResponseInfo getAdResponseInfo() {
        return this.w;
    }

    public void setAdResponseInfo(IXAdResponseInfo iXAdResponseInfo) {
        this.w = iXAdResponseInfo;
    }

    public Boolean isAdServerRequestingSuccess() {
        return this.b;
    }
}
